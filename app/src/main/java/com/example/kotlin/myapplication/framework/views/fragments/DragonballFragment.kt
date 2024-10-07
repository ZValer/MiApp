package com.example.kotlin.myapplication.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.myapplication.data.network.model.DragonballBase
import com.example.kotlin.myapplication.databinding.FragmentMovieBinding
import com.example.kotlin.myapplication.framework.adapter.DragonballAdapter
import com.example.kotlin.myapplication.framework.viewmodel.DragonballViewModel
import androidx.fragment.app.viewModels

class DragonballFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DragonballViewModel by viewModels()
    private lateinit var adapter: DragonballAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeObservers()

        // Inicializar la lista de películas llamando al ViewModel
        viewModel.getMovieList()
    }

    private fun initializeObservers() {
        viewModel.movieList.observe(viewLifecycleOwner) { movieList ->
            movieList?.let {
                setUpRecyclerView(it.results)
            }
        }
    }

    private fun setUpRecyclerView(movies: List<DragonballBase>) {
        // Configurar el RecyclerView
        binding.RVMovies.setHasFixedSize(true)
        binding.RVMovies.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        // Inicializar el adaptador y asignar la lista de películas
        adapter = DragonballAdapter(movies, requireContext())
        binding.RVMovies.adapter = adapter
    }

    // Método que se llama cuando la vista se destruye
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpia el binding para evitar fugas de memoria
    }
}
