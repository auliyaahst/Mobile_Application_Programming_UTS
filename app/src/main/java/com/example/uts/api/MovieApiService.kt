package com.example.uts.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular?api_key=662046af315e0ee6b310d8a626cd3a6a")
    fun searchMovies(
        @Query("page") page: Int = 1
    ): Call<String>
}
