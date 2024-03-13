package com.example.movie_sample_jetpack.ui.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie_sample_jetpack.ui.api.ApiInterface
import com.example.movie_sample_jetpack.ui.model.Movie
import com.example.movie_sample_jetpack.ui.utils.Constants
import com.example.movie_sample_jetpack.ui.utils.Constants.ACCESS_TOKEN

class MainPagingSource(private val apiInterface: ApiInterface) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = apiInterface.getPopularMovies("Bearer $ACCESS_TOKEN", page)
            Log.i(TAG, response.body()?.results.toString())
            Log.i(TAG, "Loaded page: $page, pageSize: ${params.loadSize}")
            LoadResult.Page(
                data = response.body()?.results ?: emptyList(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.body()!!.results.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            Log.i("abhay", e.message.toString())
            LoadResult.Error(e)
        }
    }

    companion object {
        const val TAG = "Paging Source"
    }
}