package com.example.kotlin.myapplication.framework.adapter.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.myapplication.data.network.model.DragonballBase
import com.example.kotlin.myapplication.databinding.ItemDragonballBinding

// DragonballViewHolder gestiona cómo se muestran los elementos individuales en un RecyclerView.
class DragonballViewHolder(private val binding: ItemDragonballBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DragonballBase, context: Context) {
        binding.TVTitle.text = item.name // Asigna el nombre del personaje
        binding.TVDescription.text = item.description // Asigna la descripción

        // Cargar la imagen del personaje usando Glide
        Glide.with(context)
            .load(item.image) // URL de la imagen del personaje
            .into(binding.IVPoster)
    }
}
