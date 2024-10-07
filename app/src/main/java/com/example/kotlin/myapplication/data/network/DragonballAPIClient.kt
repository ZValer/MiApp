package com.example.kotlin.myapplication.data.network

import com.example.kotlin.myapplication.data.network.model.DragonballObject
import com.example.kotlin.myapplication.utilities.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DragonballAPIClient {
    private lateinit var api: DragonballAPIService

    suspend fun getCharacters(page: Int): DragonballObject? {
        api = DragonballNetworkModuleDI()
        return try{
            api.getCharacters(
                page = page,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null // Return null in case of an exception
        }
    }
}