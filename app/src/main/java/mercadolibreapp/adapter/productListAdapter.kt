package com.moises.mercadolibreapp.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.moises.mercadolibreapp.R
import com.moises.mercadolibreapp.activities.MainActivity
import com.moises.mercadolibreapp.model.Product
import com.moises.mercadolibreapp.model.SearchResponse
import com.moises.mercadolibreapp.service.ApiSearchImp
import com.moises.mercadolibreapp.service.ApiSearchInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row_post.view.*
import mercadolibreapp.activities.ProductActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException


class productListAdapter(val context: Context, mainActivity: MainActivity) : RecyclerView.Adapter<ProductListViewHolder>() {


    private val apiSearchImp: ApiSearchImp = ApiSearchImp()
    private val apiSearchInterface: ApiSearchInterface = apiSearchImp.mainServiceCall()
    private var items: List<Product>? = null
    private val mainActivity = mainActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_row_post, parent, false)

        return ProductListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (items.isNullOrEmpty()) {
            0
        } else {
            items!!.size
        }
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        var product: Product = items!![position]

        Picasso.get().load(product.thumbnail.toString()).into(holder.itemView.ivThumbnail)

        holder.itemView.tvTitle.text = product.title
        holder.itemView.tvPrice.text = "$" + product.price.toString()


         holder.itemView.setOnClickListener {

             val intent = Intent(holder.itemView.context, ProductActivity::class.java)

             intent.putExtra("id",product.id)
             intent.putExtra("title",product.title)
             intent.putExtra("price",product.price.toString())

             holder.itemView.context.startActivity(intent)
        }

    }

    fun getListaProductos(busqueda: String){

        apiSearchInterface.getProducts(busqueda).enqueue(object: Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e(context.getString(R.string.LOG_TAG),evaluateFailure(context,t))
                mainActivity.showErrorSplash()
                notifyDataSetChanged()
            }

            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {

                if (response.isSuccessful && response.code() == 200){

                    var rta = response.body()

                    items = rta!!.results

                    if (items.isNullOrEmpty()){
                        mainActivity.showEmptyResultSplash()
                        //showEmptyResultSplash() or something
                    }
                    Log.i(context.getString(R.string.LOG_TAG), "Datos obtenidos del servicio de mercado libre: "+items!!.size.toString())
                    Log.d(context.getString(R.string.LOG_TAG),"el json obtenindo: "+ Gson().toJson(items!!).toString())
                    notifyDataSetChanged()
                }else{
                    Log.e(context.getString(R.string.LOG_TAG),context.getString(R.string.server_error_message))
                }

            }

        })

    }

    private fun evaluateFailure(context: Context, t: Throwable): String {
        return when (t) {
            is UnknownHostException -> context.getString(R.string.connection_message)
            is SocketTimeoutException -> context.getString(R.string.time_out_message)
            is SSLHandshakeException -> context.getString(R.string.connection_lost_message)
            else -> context.getString(R.string.default_error_message)
        }
    }

}