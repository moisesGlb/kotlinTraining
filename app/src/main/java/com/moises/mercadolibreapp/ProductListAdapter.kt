package com.moises.mercadolibreapp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.moises.mercadolibreapp.model.Product
import com.moises.mercadolibreapp.model.SearchResponse
import com.moises.mercadolibreapp.presenter.ProductPresenter
import com.moises.mercadolibreapp.service.ApiSearchImp
import com.moises.mercadolibreapp.service.ApiSearchInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lista_de_productos.view.*
import kotlinx.android.synthetic.main.item_row_post.view.*
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListAdapter(val context: Context): RecyclerView.Adapter<ProductListViewHolder>() {



    private val apiSearchImp: ApiSearchImp = ApiSearchImp()
    private val apiSearchInterface: ApiSearchInterface = apiSearchImp.mainServiceCall()
    private var items: List<Product>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_row_post,parent,false)

        return ProductListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if  (items.isNullOrEmpty()) {
            0
        } else{
            items!!.size
        }
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        var product: Product = items!![position]

       Picasso.get().load(product.thumbnail.toString()).error(R.drawable.icon).into(holder.itemView.ivImage)

       holder.itemView.tvTitle.text = product.title
       holder.itemView.tvPrice.text = "$"+product.price.toString()

        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context,ProductActivity::class.java)

            intent.putExtra("id",product.id)
            intent.putExtra("title",product.title)
            intent.putExtra("price",product.price.toString())

            holder.itemView.context.startActivity(intent)
        }


    }


    fun getListaProductos(busqueda: String){

        apiSearchInterface.getProducts(busqueda).enqueue(object: Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.i("MercadoLibreApp", "revento por todos lados..."+t.message)
                t.printStackTrace()
                notifyDataSetChanged()
            }

            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                var rta = response.body()

                items = rta!!.results

                Log.i("MercadoLibreApp", "Datos obtenidos del servicio de mercado libre: "+items!!.size.toString())
                Log.d("MercadoLibreApp","el json obtenindo: "+ Gson().toJson(items!!).toString())
                notifyDataSetChanged()


            }

        })

    }

}