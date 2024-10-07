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

// DragonballViewModel maneja los datos para MainActivity y se comunica con la capa de dominio
class DragonballViewModel : ViewModel() {
    val movieList = MutableLiveData<List<DragonballBase>?>() // Lista original
    private val filteredMovieList = MutableLiveData<List<DragonballBase>?>() // Lista filtrada
    private val movieListRequirement = DragonballListRequirement()
    private var currentPage = 1 // Número de página actual
    private var isLoading = false // Bandera para evitar múltiples cargas

    // Obtener la lista inicial
    fun getDragonballList() {
        loadDragonballs(currentPage)
    }

    // Cargar más elementos (paginación)
    fun loadMoreDragonballs() {
        if (!isLoading) {
            Log.d("TAG", "Loading more dragonballs, currentPage: $currentPage")
            currentPage++
            loadDragonballs(currentPage)
        } else {
            Log.d("TAG", "Already loading, skipping...")
        }
    }

    private fun loadDragonballs(page: Int) {
        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG", "Requesting page: $page")
            val result: DragonballObject? = movieListRequirement(page)
            Log.d("TAG", "Received result: $result")
            CoroutineScope(Dispatchers.Main).launch {
                if (result != null && result.results.isNotEmpty()) {
                    val updatedList = movieList.value?.toMutableList() ?: mutableListOf()
                    updatedList.addAll(result.results)
                    movieList.postValue(updatedList)
                    filteredMovieList.postValue(updatedList)
                } else {
                    Log.e("DragonballViewModel", "Error: No data received for page $page or results list is empty")
                    movieList.postValue(emptyList())
                    filteredMovieList.postValue(emptyList())
                }
                isLoading = false
            }
        }
    }


    // Filtrar personajes por género
    fun filterByGender(gender: String) {
        val originalList = movieList.value ?: return
        val filteredList = originalList.filter { it.gender.equals(gender, ignoreCase = true) }
        filteredMovieList.postValue(filteredList)
    }

    // Buscar personajes por nombre
    fun searchCharacters(query: String) {
        val originalList = movieList.value ?: return
        val filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter { it.name.contains(query, ignoreCase = true) }
        }
        filteredMovieList.postValue(filteredList)
    }

    // Retornar la lista filtrada
    fun getFilteredMovieList(): MutableLiveData<List<DragonballBase>?> {
        return filteredMovieList
    }
}
