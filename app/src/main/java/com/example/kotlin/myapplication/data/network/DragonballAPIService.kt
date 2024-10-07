package com.example.kotlin.myapplication.data.network

import com.example.kotlin.myapplication.data.network.model.DragonballObject
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DragonballAPIService {
    // Ejemplo de endpoint: https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc
    @GET("discover/movie")
    @Headers(
        "accept: application/json",
        "Authorization: Bearer x"
    )
    suspend fun getMovieList(
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String
    ): DragonballObject
}
