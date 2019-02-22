package com.moises.redditapp.service

import io.reactivex.Observable
import retrofit2.http.GET

interface IRetrofit {
    @GET("top.json")
    fun getPost() : Observable<RedditPostData>


}