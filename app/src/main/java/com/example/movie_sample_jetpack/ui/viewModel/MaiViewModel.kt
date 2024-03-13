package com.example.movie_sample_jetpack.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movie_sample_jetpack.ui.model.Movie
import com.example.movie_sample_jetpack.ui.retrofit.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class MaiViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    fun getMovie(): Flow<PagingData<Movie>> =
        repository.getMovies().cachedIn(viewModelScope).catch{ e ->
            Log.e("AbhaySaini", "Error fetching movies: ${e.message}", e)
        }
}