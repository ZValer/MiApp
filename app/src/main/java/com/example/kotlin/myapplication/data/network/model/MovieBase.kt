package com.example.kotlin.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

// https://api.themoviedb.org/3/find/{external_id}

data class MovieBase (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("poster_path") val poster_path: String
)