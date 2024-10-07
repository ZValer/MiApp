package com.example.kotlin.myapplication.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.myapplication.data.network.model.DragonballBase
import com.example.kotlin.myapplication.domain.DragonballListRequirement
import kotlinx.coroutines.launch

class DragonballViewModel : ViewModel() {

    val charactersList = MutableLiveData<List<DragonballBase>?>()
    private val filteredCharactersList = MutableLiveData<List<DragonballBase>?>()

    private val dragonballListRequirement = DragonballListRequirement()

    // Obtener los personajes a través de una llamada a la API
    fun getCharacters() {
        viewModelScope.launch {
            try {
                val result = dragonballListRequirement()
                if (result != null && result.items != null) {
                    charactersList.postValue(result.items)
                    filteredCharactersList.postValue(result.items) // Inicializa la lista filtrada
                } else {
                    Log.e("DragonballViewModel", "Error: No data received or items list is null")
                    Log.d("DragonballViewModel", "Response: $result")
                    charactersList.postValue(emptyList())
                    filteredCharactersList.postValue(emptyList())
                }
            } catch (e: Exception) {
                Log.e("DragonballViewModel", "Exception: ${e.message}")
                charactersList.postValue(emptyList())
                filteredCharactersList.postValue(emptyList())
            }
        }
    }

    // Filtrar personajes por género
    fun filterByGender(gender: String) {
        val originalList = charactersList.value ?: return // Obtener la lista original
        val filteredList = originalList.filter { it.gender.equals(gender, ignoreCase = true) } // Filtrar por género
        filteredCharactersList.postValue(filteredList) // Actualiza la lista filtrada
    }

    // Buscar personajes por nombre
    fun searchCharacters(query: String) {
        val originalList = charactersList.value ?: return
        val filteredList = if (query.isEmpty()) {
            originalList // Si no hay texto de búsqueda, mostrar todos los personajes
        } else {
            originalList.filter { it.name.contains(query, ignoreCase = true) } // Filtrar por nombre
        }
        filteredCharactersList.postValue(filteredList) // Actualizar la lista filtrada
    }

    // Retornar la lista filtrada
    fun getFilteredCharacters(): MutableLiveData<List<DragonballBase>?> {
        return filteredCharactersList
    }
}
