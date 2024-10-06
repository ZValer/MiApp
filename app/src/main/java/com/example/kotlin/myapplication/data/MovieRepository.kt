package com.example.kotlin.myapplication.data

import com.example.kotlin.myapplication.data.network.MovieAPIClient
import com.example.kotlin.myapplication.data.network.model.MovieBase
import com.example.kotlin.myapplication.data.network.model.MovieObject

// Intermediario entre el Requirementy la fuente de datos (API).
class MovieRepository() {
    private val apiMovie = MovieAPIClient()

    suspend fun getMovieList(limit: Int): MovieObject? =
        apiMovie.getMovieList(limit)

    suspend fun getMovieInfo(numberMovie: Int): MovieBase? =
        apiMovie.getMovieInfo(numberMovie)
}