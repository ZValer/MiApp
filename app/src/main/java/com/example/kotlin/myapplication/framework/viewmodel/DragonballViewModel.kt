package com.example.kotlin.myapplication.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.myapplication.data.network.model.DragonballBase
import com.example.kotlin.myapplication.data.network.model.DragonballObject
import com.example.kotlin.myapplication.domain.DragonballListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// MovieViewModel maneja los datos para MainActivity y se comunica con la capa de dominio
class DragonballViewModel : ViewModel() {

    val charactersList = MutableLiveData<List<DragonballBase>?>()
    private val filteredCharactersList = MutableLiveData<List<DragonballBase>?>() // Nueva lista filtrada

    private val dragonballListRequirement = DragonballListRequirement()

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val result = dragonballListRequirement()
                if (result != null && result.items != null) {
                    charactersList.postValue(result.items)
                    filteredCharactersList.postValue(result.items) // Inicializa la lista filtrada
                } else {
                    Log.e("DragonballViewModel", "Error: No data received or items list is null")
                    Log.d("DragonballViewModelresults", "Response: $result")
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

    // Método para buscar personajes
    fun searchCharacters(query: String) {
        val originalList = charactersList.value ?: return // Obtener la lista original
        if (query.isEmpty()) {
            filteredCharactersList.postValue(originalList) // Si la búsqueda está vacía, mostrar todos
        } else {
            val filteredList = originalList.filter {
                it.name.contains(query, ignoreCase = true) // Filtrar por nombre
            }
            filteredCharactersList.postValue(filteredList) // Actualiza la lista filtrada
        }
    }

    fun getFilteredCharacters(): MutableLiveData<List<DragonballBase>?> {
        return filteredCharactersList
    }
}
