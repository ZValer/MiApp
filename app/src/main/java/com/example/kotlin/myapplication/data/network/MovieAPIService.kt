package com.example.kotlin.myapplication.data.network

import com.example.kotlin.myapplication.data.network.model.MovieBase
import com.example.kotlin.myapplication.data.network.model.MovieObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPIService {

    // Endpoint: https://api.themoviedb.org/3/discover/movie
    // Función para obtener una lista de peliculas desde la API.
    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("limit") limit: Int
    ): MovieObject

    // Endpoint: // https://api.themoviedb.org/3/find/{external_id}
    // Función para obtener información detallada sobre una película.
    @GET("find/{external_id}")
    suspend fun getMovieInfo(
        // @Path se usa para sustituir el valor de id en la URL.
        @Path("numberPokemon") numberPokemon: Int
    ): MovieBase
}
