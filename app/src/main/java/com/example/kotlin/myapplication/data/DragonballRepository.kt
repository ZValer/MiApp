package com.example.kotlin.myapplication.data

import android.util.Log
import com.example.kotlin.myapplication.data.network.DragonballAPIClient
import com.example.kotlin.myapplication.data.network.DragonballAPIService
import com.example.kotlin.myapplication.data.network.model.DragonballObject

// Intermediario entre el Requirementy la fuente de datos (API).
class DragonballRepository {
    private val api = DragonballAPIClient()

    suspend fun getCharacters(page: Int) = api.getCharacters(page)
}
