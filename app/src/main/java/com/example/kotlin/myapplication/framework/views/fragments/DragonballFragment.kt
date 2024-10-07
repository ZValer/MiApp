package com.example.kotlin.myapplication.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.myapplication.data.network.model.DragonballBase
import com.example.kotlin.myapplication.databinding.FragmentDragonballBinding
import com.example.kotlin.myapplication.framework.adapter.DragonballAdapter
import com.example.kotlin.myapplication.framework.viewmodel.DragonballViewModel
import androidx.fragment.app.viewModels

class DragonballFragment : Fragment() {

    private var _binding: FragmentDragonballBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DragonballViewModel by viewModels()
    private lateinit var adapter: DragonballAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDragonballBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar el RecyclerView
        binding.RVDragonball.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getFilteredCharacters().observe(viewLifecycleOwner) { charactersList ->
            charactersList?.let {
                setUpRecyclerView(it) // Llamar al método para configurar el RecyclerView
            }
        }

        // Obtener los personajes a través del ViewModel
        viewModel.getCharacters()

        // Configurar el EditText para buscar personajes
        binding.searchCharacter.addTextChangedListener { text ->
            viewModel.searchCharacters(text.toString()) // Llamar al método de búsqueda
        }

        // Configurar botones para filtrar por género
        binding.btnHombres.setOnClickListener {
            viewModel.filterByGender("Male") // Filtrar por personajes masculinos
        }

        binding.btnMujeres.setOnClickListener {
            viewModel.filterByGender("Female") // Filtrar por personajes femeninos
        }
    }

    private fun setUpRecyclerView(characters: List<DragonballBase>) {
        adapter = DragonballAdapter(characters, requireContext())
        binding.RVDragonball.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
