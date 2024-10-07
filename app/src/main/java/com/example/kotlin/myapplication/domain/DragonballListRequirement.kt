package com.example.kotlin.myapplication.domain

import com.example.kotlin.myapplication.data.DragonballRepository


// Caso de uso en la capa de dominio.
class DragonballListRequirement {
    private val repository = DragonballRepository()

    // Cambia el operador para que acepte un parámetro de página
    suspend operator fun invoke(page: Int) = repository.getCharacters(page)
}

