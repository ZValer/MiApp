package com.example.kotlin.myapplication.framework.views.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.myapplication.data.network.model.DragonballBase
import com.example.kotlin.myapplication.databinding.FragmentDragonballBinding
import com.example.kotlin.myapplication.framework.adapter.DragonballAdapter
import com.example.kotlin.myapplication.framework.viewmodel.DragonballViewModel
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView

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

        // Inicializa el RecyclerView
        setUpRecyclerView()

        // Inicializa los observadores
        initializeObservers()

        // Obtener la lista inicial de personajes
        viewModel.getDragonballList()

        // Configurar el ScrollListener para cargar más personajes al hacer scroll
        binding.RVDragonball.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMoreDragonballs()
                }
            }
        })

        // Configurar el Spinner (Dropdown) para filtrar por raza
        val races = listOf("Todos", "Saiyan", "Namekian", "Human", "Android") // Agrega las razas necesarias
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, races)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.raceSpinner.adapter = spinnerAdapter

        // Configurar el listener del Spinner
        binding.raceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedRace = if (position == 0) null else races[position]
                viewModel.selectedRace = selectedRace
                viewModel.filterCharacters(selectedRace)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                viewModel.selectedRace = null
                viewModel.filterCharacters(null)
            }
        }
    }

    private fun setUpRecyclerView() {
        // Configurar el RecyclerView
        binding.RVDragonball.setHasFixedSize(true)
        binding.RVDragonball.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Inicializar el adaptador
        adapter = DragonballAdapter(emptyList(), requireContext())
        binding.RVDragonball.adapter = adapter
    }

    private fun initializeObservers() {
        // Observador de la lista filtrada
        viewModel.filteredMovieList.observe(viewLifecycleOwner) { movieList ->
            movieList?.let {
                adapter.updateData(it) // Actualiza los datos del adaptador
            }
        }

        // Observador de la lista completa
        viewModel.movieList.observe(viewLifecycleOwner) { movieList ->
            movieList?.let {
                viewModel.filterCharacters(viewModel.selectedRace) // Filtrar personajes según la raza seleccionada
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
