package com.example.kotlin.myapplication.framework.adapter.viewholders

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.myapplication.data.network.model.MovieBase
import com.example.kotlin.myapplication.databinding.ItemMovieBinding
import com.example.kotlin.myapplication.utilities.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// MovieViewHolder gestiona cómo se muestran los elementos individuales en un RecyclerView.
class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(item: MovieBase, context: Context) {
        // Asignar los datos de la película a los elementos de la UI
        binding.TVTitle.text = item.title
        binding.TVReleaseDate.text = item.release_date
        binding.TVDescription.text = item.overview

        // Cargar la imagen del póster usando Glide
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500" + item.poster_path) // Ajusta el tamaño del póster según necesites
            .into(binding.IVPoster) // Asegúrate de que IVPoster sea el ImageView para el póster
    }
}