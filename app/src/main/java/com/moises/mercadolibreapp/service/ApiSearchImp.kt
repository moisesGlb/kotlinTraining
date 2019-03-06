package com.moises.mercadolibreapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiSearchImp {

    var retrofit: Retrofit? = null

    fun mainServiceCall(): ApiSearchInterface  {

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit!!.create(ApiSearchInterface::class.java)

    }
}