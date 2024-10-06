package com.example.kotlin.myapplication.framework.views.activities


import android.app.Activity
import android.os.Bundle
import com.example.kotlin.myapplication.databinding.ActivityMainBinding


class MainActivity : Activity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout y obtener el binding
        _binding = ActivityMainBinding.inflate(layoutInflater)

        // Establece el contenido de la actividad usando el binding
        setContentView(binding.root)
    }
}


