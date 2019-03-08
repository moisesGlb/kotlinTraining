package com.moises.mercadolibreapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.moises.mercadolibreapp.controller.ProductController
import com.moises.mercadolibreapp.model.imgSearchList
import com.moises.mercadolibreapp.presenter.ProductPresenter
import com.moises.mercadolibreapp.service.RestApi
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class ProductActivity : AppCompatActivity(), ProductController.View {

    private lateinit var myJob: Job

    private val productPresenter = ProductPresenter(this@ProductActivity)

    private var imgUrl: String? = null

    private lateinit var id: String

    private var descriptionProgressBar: ProgressBar? = null
    private var imageView: ImageView? = null
    private var title: TextView? = null
    private var price: TextView? = null
    private var description: TextView? = null
    private var imageProgressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val service = RestApi.instanceClient()

        productPresenter.controller = this

        imageView = findViewById(R.id.ivImage)
        title = findViewById(R.id.tvTitleDetail)
        price = findViewById(R.id.tvPriceDetail)
        description = findViewById(R.id.tvDescription)
        imageProgressBar = findViewById(R.id.imgProgress)
        descriptionProgressBar = findViewById(R.id.descriptionProgresBar)

        id = intent.extras!!.getString("id")

        title!!.text = intent.extras!!.getString("title")

        price!!.text = "$"+ intent.extras!!.getString("price")

        myJob = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                productPresenter.loadImg(id)

            }
        }


    }

    override fun onDestroy() {
        myJob.cancel()
        super.onDestroy()
    }

    override fun onLoadImgSuccessful(imgSrchLst: imgSearchList) {
        imageProgressBar!!.visibility = View.GONE

        Toast.makeText(this, "el servicio obtuvo la imagen: "+imgSrchLst.pictures.size, Toast.LENGTH_SHORT).show()

        imgUrl = imgSrchLst.pictures[0].url

    }

    override fun onLoadImgFailed(message: String) {
        imageProgressBar!!.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun setImage(url: String){

        if(!url.isNullOrEmpty()){
            imageProgressBar!!.visibility = View.GONE

            if (imageView != null){
                Picasso.get().load(url)
                    .placeholder(R.drawable.background)
                    .error(R.drawable.icon)
                    .into(imageView)
            }
        }



    }
}
