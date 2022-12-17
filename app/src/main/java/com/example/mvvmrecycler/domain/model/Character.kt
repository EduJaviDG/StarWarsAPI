package com.example.mvvmrecycler.domain.model

import com.google.gson.annotations.SerializedName

data class Character (

        @SerializedName("name") var name: String,
        @SerializedName("height") var height: String,
        @SerializedName("mass") var mass: String,
        @SerializedName("hair_color") var hair: String,
        @SerializedName("skin_color") var skin: String,
        @SerializedName("eye_color") var eye: String,
        @SerializedName("birth_year") var birth: String,
        @SerializedName("gender") var gender: String,
        @SerializedName("homeworld") var homeworld: String,
        @SerializedName("films") var films: List<String>,
        @SerializedName("species") var species: List<String>,
        @SerializedName("vehicles") var vehicles: List<String>,
        @SerializedName("starships") var starships: List<String>,
        @SerializedName("created") var created: String,
        @SerializedName("edited") var edited: String,
        @SerializedName("url") var url: String

        )