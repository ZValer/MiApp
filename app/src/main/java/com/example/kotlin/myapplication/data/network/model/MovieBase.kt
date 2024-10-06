package com.example.kotlin.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

// https://api.themoviedb.org/3/find/{external_id}

data class MovieBase(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,  // Using camelCase for Kotlin property
    @SerializedName("poster_path") val url: String,  // Using camelCase for Kotlin property
    @SerializedName("vote_average") val voteAverage: Float  // Using camelCase for Kotlin property
)