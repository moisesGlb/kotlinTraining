package com.moises.mercadolibreapp.presenter

import android.content.Context
import android.util.Log
import com.moises.mercadolibreapp.R
import com.moises.mercadolibreapp.controller.ProductController
import com.moises.mercadolibreapp.service.ApiSearchImpBackground
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class ProductPresenter(private val context: Context): ProductController.Presenter{


    private val restApi = ApiSearchImpBackground.instanceClient()
    lateinit var controller: ProductController.View


    override suspend fun loadImg(id: String) {
        try {
            val response = restApi.getImage(id).await()
            if (response.isSuccessful){
                Log.i(context.getString(R.string.LOG_TAG),"Anduvo la llamada al servicio")
                controller.onLoadImgSuccessful(response.body()!!)

            }else{
                controller.onLoadImgFailed(context.getString(R.string.server_error_message))
            }
        }catch (ex: Exception){
            Log.i(context.getString(R.string.LOG_TAG),evaluateFailure(context, ex))

            controller.onLoadImgFailed(evaluateFailure(context, ex))

        }
    }

    override suspend fun loadDescription(id: String) {
        try {
            val response = restApi.getDescription(id).await()
            if (response.isSuccessful){
                Log.i(context.getString(R.string.LOG_TAG),"Anduvo la llamada al servicio")
                controller.onLoadDescriptionSuccessful(response.body()!!)

            }else{
                controller.onLoadDescriptionFailed(context.getString(R.string.server_error_message))
            }
        }catch (ex: Exception){
            Log.i(context.getString(R.string.LOG_TAG),evaluateFailure(context, ex))
            controller.onLoadDescriptionFailed(evaluateFailure(context, ex))

        }
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