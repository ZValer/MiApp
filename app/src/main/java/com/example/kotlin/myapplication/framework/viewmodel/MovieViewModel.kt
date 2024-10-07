package com.example.kotlin.myapplication.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.myapplication.data.network.model.MovieObject
import com.example.kotlin.myapplication.domain.MovieListRequirement
import com.example.kotlin.myapplication.utilities.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// MovieViewModel maneja los datos para MainActivity y se comunica con la capa de dominio
class MovieViewModel : ViewModel() {

    val movieList = MutableLiveData<MovieObject?>()
    private val movieListRequirement = MovieListRequirement()
    private var currentPage = 1 // Número de página actual
    private var isLoading = false // Bandera para evitar múltiples cargas

    fun getMovieList() {
        loadMovies(currentPage)
    }

    fun loadMoreMovies() {
        if (!isLoading) {
            currentPage++
            loadMovies(currentPage)
        }
    }

    private fun loadMovies(page: Int) {
        isLoading = true // Evita múltiples cargas
        viewModelScope.launch(Dispatchers.IO) {
            val result: MovieObject? = movieListRequirement(
                includeAdult = false,
                includeVideo = false,
                page = page,
                sortBy = "popularity.desc"
            )
            Log.d("TAG", "loadMovies: $result")
            CoroutineScope(Dispatchers.Main).launch {
                movieList.postValue(result)
                isLoading = false // Permitir nuevas cargas
            }
        }
    }
}
