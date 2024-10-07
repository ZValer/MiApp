package com.example.kotlin.myapplication.framework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.myapplication.data.network.model.DragonballBase
import com.example.kotlin.myapplication.data.network.model.DragonballObject
import com.example.kotlin.myapplication.domain.DragonballListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// DragonballViewModel maneja los datos para MainActivity y se comunica con la capa de dominio
class DragonballViewModel : ViewModel() {
    val movieList = MutableLiveData<List<DragonballBase>?>() // Lista original
    val filteredMovieList = MutableLiveData<List<DragonballBase>?>() // Lista filtrada
    private val movieListRequirement = DragonballListRequirement()
    private var currentPage = 1 // Número de página actual
    private var isLoading = false // Bandera para evitar múltiples cargas
    var selectedRace: String? = null // Raza seleccionada

    // Obtener la lista inicial
    fun getDragonballList() {
        loadDragonballs(currentPage)
    }

    // Cargar más elementos (paginación)
    fun loadMoreDragonballs() {
        if (!isLoading) {
            currentPage++
            loadDragonballs(currentPage)
        }
    }

    private fun loadDragonballs(page: Int) {
        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            val result: DragonballObject? = movieListRequirement(page)
            CoroutineScope(Dispatchers.Main).launch {
                if (result != null && result.results.isNotEmpty()) {
                    val updatedList = movieList.value?.toMutableList() ?: mutableListOf()
                    updatedList.addAll(result.results)
                    movieList.postValue(updatedList)
                    filterCharacters(selectedRace) // Filtrar después de cargar
                }
                isLoading = false
            }
        }
    }

    // Filtrar personajes por raza
    fun filterCharacters(race: String?) {
        filteredMovieList.value = movieList.value?.filter {
            race == null || it.race == race // Asegúrate de que el modelo DragonballBase tenga la propiedad 'race'
        }
    }
}

