package com.example.kotlin.myapplication.data.network

import com.example.kotlin.myapplication.data.network.model.DragonballObject
import retrofit2.http.GET
import retrofit2.http.Query

interface DragonballAPIService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int? = null
    ): DragonballObject
}

