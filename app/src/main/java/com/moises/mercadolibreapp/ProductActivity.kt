package com.moises.mercadolibreapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.moises.mercadolibreapp.model.ImageSearch
import com.moises.mercadolibreapp.model.ProductDescription
import com.moises.mercadolibreapp.service.ApiSearchImp
import com.moises.mercadolibreapp.service.ApiSearchInterface
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    private val apiSearchImp: ApiSearchImp = ApiSearchImp()
    private val apiSearchInt: ApiSearchInterface = apiSearchImp.mainServiceCall()


    private var imageList: List<ImageSearch>? = null

    private lateinit var id: String

    private var imageView: ImageView? = null
    private var title: TextView? = null
    private var price: TextView? = null
    private var description: TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)



        imageView = findViewById(R.id.ivImage)
        title = findViewById(R.id.tvTitleDetail)
        price = findViewById(R.id.tvPriceDetail)
        description = findViewById(R.id.tvDescription)

        id = intent.extras!!.getString("id")

        title!!.text = intent.extras!!.getString("title")

        price!!.text = "$"+ intent.extras!!.getString("price")


        getDescription(id)

        if(!description!!.text.isNullOrEmpty()){
            description!!.visibility = View.VISIBLE
        }else{
            description!!.visibility = View.GONE
        }

       /* getImage(id)

        if (!imageList.isNullOrEmpty()){

            Picasso.get().load(imageList!![0].url)
                .error(R.drawable.icon)
                .placeholder(R.color.placeholder_grey)
                .into(imageView)

        }*/

    }

    private fun getImage(id: String){
        apiSearchInt.getImage(id).enqueue(object: Callback<ImageSearch>{
            override fun onFailure(call: Call<ImageSearch>, t: Throwable) {
                Log.i("MercadoLibreApp", "fallo la llamada a la api Obtener Imagenes"+t.message)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ImageSearch>, response: Response<ImageSearch>) {
                imageList = response.body()!!.pictures

                Log.i("MercadoLibreApp", "Datos obtenidos correctamente de la api Obtener Imagenes: "+imageList!!.size)
            }

        })
    }

    private fun getDescription(id: String){

        apiSearchInt.getDescription(id).enqueue(object: Callback<ProductDescription>{
            override fun onFailure(call: Call<ProductDescription>, t: Throwable) {
                Log.i("MercadoLibreApp", "fallo la llamada a la api descripcion de producto"+t.message)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ProductDescription>, response: Response<ProductDescription>) {
                description!!.text = response.body()!!.plain_text

                Log.i("MercadoLibreApp", "Datos obtenidos correctamente del servicio descripcion de producto")
                Log.d("MercadoLibreApp","el json obtenindo: "+ Gson().toJson( description!!.text))

            }
        })

    }
}
