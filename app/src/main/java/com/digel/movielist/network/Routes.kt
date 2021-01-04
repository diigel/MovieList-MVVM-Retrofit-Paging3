package com.digel.movielist.network

import com.digel.movielist.R
import com.digel.movielist.entity.ResponseMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface Routes {

    @GET("movie/popular")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = BaseApplication.getApplicationContext().applicationContext.applicationContext.resources.getString(R.string.api_key),
        @Query("language") language  : String = "en-US",
        @Query("page") page  : Int
    ): ResponseMovie
}