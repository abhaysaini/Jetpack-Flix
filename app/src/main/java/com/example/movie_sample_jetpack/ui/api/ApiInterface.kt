package com.example.movie_sample_jetpack.ui.api

import com.example.movie_sample_jetpack.ui.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") authorization: String,
        @Query("page") page : Int
    ):Response<MovieResponse>
}