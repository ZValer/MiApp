package com.example.kotlin.myapplication.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.kotlin.myapplication.utilities.Constants

object MovieNetworkModuleDI {
    // GsonConverterFactory convierte JSON de la API en objetos de Kotlin.
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()

    // OkHttpClient es utilizado por Retrofit para gestionar las solicitudes de red.
    private val okHttpClient: OkHttpClient = OkHttpClient()

    // se crea y configura el objeto Retrofit para realizar llamadas de red.
    operator fun invoke(): MovieAPIService =
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(MovieAPIService::class.java)
}
