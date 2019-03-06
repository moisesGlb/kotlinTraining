package com.moises.mercadolibreapp.service

import com.moises.mercadolibreapp.model.SearchResponse
import com.moises.mercadolibreapp.model.ImageSearch
import com.moises.mercadolibreapp.model.ProductDescription
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiSearchInterface {

    @GET("/items/{id}")
    fun getImage(@Path("id")id: String): Call<ImageSearch>

    @GET("/sites/MLA/search")
    fun getProducts(@Query("q")query: String): Call<SearchResponse>

    @GET("/items/{id}/description")
    fun getDescription(@Path("id")id: String): Call<ProductDescription>

}