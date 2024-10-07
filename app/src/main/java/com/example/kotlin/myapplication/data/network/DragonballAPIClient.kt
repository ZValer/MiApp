package com.example.kotlin.myapplication.data.network

import com.example.kotlin.myapplication.data.network.model.DragonballObject

class DragonballAPIClient {
    private lateinit var api: DragonballAPIService

    suspend fun getMovieList(includeAdult: Boolean, includeVideo: Boolean, page: Int, sortBy: String): DragonballObject? { // Return nullable MovieObject
        // Inicializa el PokemonAPIService utilizando el NetworkModuleDI, que configura la instancia de Retrofit.
        api = DragonballNetworkModuleDI()

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