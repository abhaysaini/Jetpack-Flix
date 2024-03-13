package com.example.movie_sample_jetpack.ui.retrofit

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.movie_sample_jetpack.ui.api.ApiInterface
import com.example.movie_sample_jetpack.ui.paging.MainPagingSource
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getMovies() = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { MainPagingSource(apiInterface) }
    ).flow
}