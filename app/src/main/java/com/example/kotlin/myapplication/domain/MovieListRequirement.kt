package com.example.kotlin.myapplication.domain

import com.example.kotlin.myapplication.data.MovieRepository
import com.example.kotlin.myapplication.data.network.model.MovieObject


// Caso de uso en la capa de dominio.
class MovieListRequirement {
    private val repository = MovieRepository()

    suspend operator fun invoke( limit: Int ): MovieObject? = repository.getMovieList(limit)
}