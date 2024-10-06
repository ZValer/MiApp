package com.example.kotlin.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

// https://api.themoviedb.org/3/discover/movie

data class MovieObject(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: ArrayList<MovieBase>,
    @SerializedName("total_results") val totalResults: Int,  // Using a camelCase variable name in Kotlin
    @SerializedName("total_pages") val totalPages: Int  // Using a camelCase variable name in Kotlin
)
