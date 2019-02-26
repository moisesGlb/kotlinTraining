package com.moises.redditapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceImp {

    var retrofit: Retrofit? = null

    fun commonServiceCall(): ApiService {
      retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit!!.create(ApiService::class.java)

    }


    fun customServiceCall(after: String, limit: String): ApiService  {

        retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit!!.create(ApiService::class.java)

    }

}