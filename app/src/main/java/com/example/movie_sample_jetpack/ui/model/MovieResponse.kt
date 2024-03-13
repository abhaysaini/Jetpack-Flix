package com.example.movie_sample_jetpack.ui.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class MovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

