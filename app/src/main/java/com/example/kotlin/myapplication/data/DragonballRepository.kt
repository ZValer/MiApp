package com.example.kotlin.myapplication.data

import com.example.kotlin.myapplication.data.network.DragonballAPIClient

// Intermediario entre el Requirementy la fuente de datos (API).
class DragonballRepository {
    private val api = DragonballAPIClient()

    suspend fun getCharacters() = api.getCharacters()
}
