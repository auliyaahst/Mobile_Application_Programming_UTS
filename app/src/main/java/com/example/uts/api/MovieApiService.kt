package com.example.uts.api

import com.example.uts.data.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("titles")
    fun searchMovies(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ) : Call<List<Movie>>


}