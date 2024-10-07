package com.example.kotlin.myapplication.framework.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.myapplication.data.network.model.DragonballBase
import com.example.kotlin.myapplication.databinding.ItemMovieBinding
import com.example.kotlin.myapplication.framework.adapter.viewholders.DragonballViewHolder

// MovieAdapter es la clase adaptadora para RecyclerView.
// Es responsable de proporcionar los datos que se mostrarán en cada elemento del
// RecyclerView y de crear el ViewHolder para cada elemento.
class DragonballAdapter(
    private var data: List<DragonballBase>,
    private val context: Context
) : RecyclerView.Adapter<DragonballViewHolder>() {

    // Este método enlaza los datos con el ViewHolder en una posición específica en la lista.
    override fun onBindViewHolder(holder: DragonballViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    // Método para cuando el RecyclerView necesita un nuevo ViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragonballViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DragonballViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
