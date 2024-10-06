package com.example.kotlin.myapplication.data.network

import com.example.kotlin.myapplication.data.network.model.MovieBase
import com.example.kotlin.myapplication.data.network.model.MovieObject

class MovieAPIClient {
    private lateinit var api: MovieAPIService

    suspend fun getMovieList(limit: Int): MovieObject? {
        // Inicializa el PokemonAPIService utilizando el NetworkModuleDI, que configura la instancia de Retrofit.
        api = MovieNetworkModuleDI()

        return try {
            api.getMovieList(limit)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getMovieInfo(numberMovie: Int): MovieBase? {
        api = MovieNetworkModuleDI()

        return try {
            // Llamar al método del servicio de la API para obtener información sobre un Pokémon específico.
            api.getMovieInfo(numberMovie)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }
}