package com.example.mvvmrecycler.domain.model

import com.google.gson.annotations.SerializedName

data class Character (

        @SerializedName("name") val name: String,
        @SerializedName("height") val height: String,
        @SerializedName("mass") val mass: String,
        @SerializedName("hair_color") val hair: String,
        @SerializedName("skin_color") val skin: String,
        @SerializedName("eye_color") val eye: String,
        @SerializedName("birth_year") val birth: String,
        @SerializedName("gender") val gender: String,
        @SerializedName("homeworld") val homeworld: String,
        @SerializedName("films") val films: List<String>,
        @SerializedName("species") var species: List<String>,
        @SerializedName("vehicles") var vehicles: List<String?>,
        @SerializedName("starships") var starships: List<String?>,
        @SerializedName("created") val created: String,
        @SerializedName("edited") val edited: String,
        @SerializedName("url") val url: String

        )