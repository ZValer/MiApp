package com.example.kotlin.myapplication.framework.views.activities

import android.os.Bundle
import com.example.kotlin.myapplication.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.framework.viewmodel.MainViewModel
import com.example.kotlin.myapplication.framework.views.fragments.DragonballFragment
import com.example.kotlin.myapplication.framework.views.fragments.SearchFragment
import com.example.kotlin.myapplication.framework.views.fragments.TareasFragment
import com.example.kotlin.myapplication.utilities.Constants

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //layout donde se encuentra la navbar
    private val viewModel: MainViewModel by viewModels()

    // Variable que mantiene el fragmento actualmente mostrado en la actividad
    private lateinit var currentFragment: Fragment

    // Variable para almacenar la opción de menú actual seleccionada
    private var currentMenuOption: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding() 
        //initializeObservers()
        initializeListeners()

        // Establece el fragmento inicial en la actividad como el 'MovieFragment'
        exchangeCurrentFragment(DragonballFragment(), Constants.MENU_MOVIE)
    }

    // Método para inicializar el binding con el layout de la actividad
    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)  
        setContentView(binding.root) 
    }
    
    /*
    // Método reservado para inicializar observadores de datos (puedes agregar lógica más tarde)
    private fun initializeObservers() {
        // Aquí se podrían observar cambios en LiveData del ViewModel
    }
     */

    // Método para cambiar el fragmento actual por uno nuevo
    private fun exchangeCurrentFragment(newFragment: Fragment, newMenuOption: String) {
        currentFragment = newFragment 

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, currentFragment)
            .commit() 

        currentMenuOption = newMenuOption 
    }

    // Inicializa los listeners para las interacciones con la barra de navegación
    private fun initializeListeners() {
        // Listener para el botón "Movie", el id llMovie viene del layout
        binding.appBarMain.llMovie.setOnClickListener {
            selectMenuOption(Constants.MENU_MOVIE)
        }

        binding.appBarMain.llSearch.setOnClickListener {
            selectMenuOption(Constants.MENU_SEARCH)
        }

        binding.appBarMain.llMisTareas.setOnClickListener {
            selectMenuOption(Constants.MENU_TAREAS)
        }
    }

    // Método para seleccionar una opción de menú
    public fun selectMenuOption(menuOption: String) {
        if (menuOption == currentMenuOption) {
            return
        }

        when (menuOption) {
            Constants.MENU_MOVIE -> exchangeCurrentFragment(DragonballFragment(), Constants.MENU_MOVIE)
            Constants.MENU_SEARCH -> exchangeCurrentFragment(SearchFragment(), Constants.MENU_SEARCH)
            Constants.MENU_TAREAS -> exchangeCurrentFragment(TareasFragment(), Constants.MENU_TAREAS)
        }
    }
}
