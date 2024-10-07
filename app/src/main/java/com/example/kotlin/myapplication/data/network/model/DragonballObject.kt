package com.example.kotlin.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

// https://dragonball-api.com/api/characters

data class DragonballObject (
    @SerializedName("items") val items: List<DragonballBase>,
    @SerializedName("meta") val meta: MetaBase,
    @SerializedName("links") val links: LinksBase
)
