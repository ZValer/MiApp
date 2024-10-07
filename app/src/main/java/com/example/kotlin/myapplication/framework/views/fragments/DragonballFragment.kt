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
        // Inflar el layout para este fragmento
        _binding = FragmentDragonballBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeObservers()

        // Inicializar la lista de personajes llamando al ViewModel
        viewModel.getDragonballList()

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

        // Configurar el ScrollListener para cargar más personajes
        binding.RVDragonball.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMoreDragonballs()
                }
            }
        })
    }

    private fun initializeObservers() {
        viewModel.movieList.observe(viewLifecycleOwner) { movieList ->
            movieList?.let {
                setUpRecyclerView(it) // Configurar el RecyclerView con la lista de personajes
            }
        }

        viewModel.getFilteredMovieList().observe(viewLifecycleOwner) { filteredList ->
            filteredList?.let {
                adapter.updateList(it) // Actualiza la lista del adaptador con los datos filtrados
            }
        }
    }

    private fun setUpRecyclerView(movies: List<DragonballBase>) {
        // Configurar el RecyclerView
        binding.RVDragonball.setHasFixedSize(true)
        binding.RVDragonball.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        // Inicializar el adaptador y asignar la lista de personajes
        adapter = DragonballAdapter(movies, requireContext())
        binding.RVDragonball.adapter = adapter
    }

    // Método que se llama cuando la vista se destruye
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpia el binding para evitar fugas de memoria
    }
}