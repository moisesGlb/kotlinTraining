package com.moises.mercadolibreapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiSearchImp {

    private val BASE_URL: String = "https://api.mercadolibre.com"

    var retrofit: Retrofit? = null

    fun mainServiceCall(): ApiSearchInterface  {

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit!!.create(ApiSearchInterface::class.java)

    }
}