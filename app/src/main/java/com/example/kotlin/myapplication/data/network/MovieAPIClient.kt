package com.example.kotlin.myapplication.data.network

import com.example.kotlin.myapplication.data.network.model.MovieBase
import com.example.kotlin.myapplication.data.network.model.MovieObject

class MovieAPIClient {
    private lateinit var api: MovieAPIService

    suspend fun getMovieList(includeAdult: Boolean, includeVideo: Boolean, page: Int, sortBy: String): MovieObject? { // Return nullable MovieObject
        // Inicializa el PokemonAPIService utilizando el NetworkModuleDI, que configura la instancia de Retrofit.
        api = MovieNetworkModuleDI()

        return try {
            api.getMovieList(
                includeAdult = false,
                includeVideo = false,
                language = "en-US",
                page = 1,
                sortBy = "popularity.desc"
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }
}