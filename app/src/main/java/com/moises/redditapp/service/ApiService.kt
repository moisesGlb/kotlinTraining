package com.moises.redditapp.service

import com.moises.redditapp.model.RedditPostData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/top.json")
    fun getAllPost(): Call<RedditPostData>

    @GET("/top.json")
    fun getAllPostCustom(@Query("after")after: String,@Query("limit") limit: String): Call<List<RedditPostData>>

}