package com.moises.mercadolibreapp.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.moises.mercadolibreapp.utils.Setting
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiSearchImpBackground {

    private lateinit var retrofit: Retrofit

    private val BASE_URL: String = "https://api.mercadolibre.com"

    private val okHttpClient = OkHttpClient
        .Builder()
        .readTimeout(Setting.TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Setting.TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(Setting.TIMEOUT, TimeUnit.SECONDS)
        .build()

    fun instanceClient(): ApiSearchInterface {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(ApiSearchInterface::class.java)
    }

}