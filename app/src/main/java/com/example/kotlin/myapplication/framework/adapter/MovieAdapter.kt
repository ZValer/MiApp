package com.example.kotlin.myapplication.framework.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.myapplication.data.network.model.MovieBase
import com.example.kotlin.myapplication.databinding.ItemMovieBinding
import com.example.kotlin.myapplication.framework.adapter.viewholders.MovieViewHolder

// MovieAdapter es la clase adaptadora para RecyclerView.
// Es responsable de proporcionar los datos que se mostrarán en cada elemento del
// RecyclerView y de crear el ViewHolder para cada elemento.
class MovieAdapter(
    private var data: List<MovieBase>,
    private val context: Context
) : RecyclerView.Adapter<MovieViewHolder>() {

    // Este método enlaza los datos con el ViewHolder en una posición específica en la lista.
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    // Método para cuando el RecyclerView necesita un nuevo ViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
