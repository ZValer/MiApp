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

    private val dragonballListRequirement = DragonballListRequirement()

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val result = dragonballListRequirement()
                if (result != null && result.items != null) {
                    charactersList.postValue(result.items)
                } else {
                    Log.e("DragonballViewModel", "Error: No data received or items list is null")
                    Log.d("DragonballViewModelresults", "Response: $result")
                    charactersList.postValue(emptyList()) // Enviar lista vacía si no hay datos
                }
            } catch (e: Exception) {
                Log.e("DragonballViewModel", "Exception: ${e.message}")
                charactersList.postValue(emptyList()) // En caso de excepción, enviar lista vacía
            }
        }
    }
}


