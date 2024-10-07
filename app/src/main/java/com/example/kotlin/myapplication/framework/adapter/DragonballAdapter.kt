package com.example.kotlin.myapplication.framework.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.myapplication.data.network.model.DragonballBase
import com.example.kotlin.myapplication.databinding.ItemDragonballBinding
import com.example.kotlin.myapplication.framework.adapter.viewholders.DragonballViewHolder

// DragonballAdapter es la clase adaptadora para RecyclerView.
// Es responsable de proporcionar los datos que se mostrarán en cada elemento del
// RecyclerView y de crear el ViewHolder para cada elemento.
class DragonballAdapter(
    private var data: List<DragonballBase>, // La lista de personajes Dragonball
    private val context: Context
) : RecyclerView.Adapter<DragonballViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragonballViewHolder {
        val binding = ItemDragonballBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DragonballViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DragonballViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context) // Enlaza los datos del personaje a la vista
    }

    override fun getItemCount(): Int {
        return data.size
    }

    // Método para actualizar la lista de personajes
    fun updateList(newData: List<DragonballBase>) {
        data = newData
        notifyDataSetChanged() // Notifica que la lista ha cambiado y el RecyclerView debe actualizarse
    }
}


