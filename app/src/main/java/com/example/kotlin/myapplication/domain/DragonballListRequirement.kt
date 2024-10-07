package com.example.kotlin.myapplication.domain

import com.example.kotlin.myapplication.data.DragonballRepository


// Caso de uso en la capa de dominio.
class DragonballListRequirement {
    private val repository = DragonballRepository()
    suspend operator fun invoke() = repository.getCharacters()
}