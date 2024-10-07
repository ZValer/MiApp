package com.example.kotlin.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class MetaBase(
    @SerializedName("totalItems") val total_results: Int,
    @SerializedName("itemCount") val itemCount: Int,
    @SerializedName("itemsPerPage") val itemsPerPage: Int,
    @SerializedName("totalPages") val total_pages: Int,
    @SerializedName("currentPage") val page: Int
)
