package com.example.kotlin.myapplication.data

import com.example.kotlin.myapplication.data.network.MovieAPIClient
import com.example.kotlin.myapplication.data.network.model.MovieBase
import com.example.kotlin.myapplication.data.network.model.MovieObject

// Intermediario entre el Requirementy la fuente de datos (API).
class MovieRepository {
    private val api = MovieAPIClient()

    suspend fun getMovieList(includeAdult: Boolean, includeVideo: Boolean, page: Int, sortBy: String) = api.getMovieList(includeAdult, includeVideo, page, sortBy)
}