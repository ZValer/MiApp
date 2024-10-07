package com.example.kotlin.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

// https://api.themoviedb.org/3/discover/movie

data class MovieObject (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieBase>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
)
