package com.example.kotlin.myapplication.data.network

import com.example.kotlin.myapplication.data.network.model.DragonballObject
import com.example.kotlin.myapplication.utilities.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DragonballAPIClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: DragonballAPIService = retrofit.create(DragonballAPIService::class.java)

    suspend fun getCharacters(page: Int? = null, limit: Int? = null): DragonballObject? {
        return try {
            api.getCharacters(page, limit)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}