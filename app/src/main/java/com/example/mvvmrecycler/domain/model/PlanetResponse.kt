package com.example.mvvmrecycler.domain.model

import com.google.gson.annotations.SerializedName

data class PlanetResponse(

    @SerializedName("name") val name: String,
    @SerializedName("climate") val climate: String,
    @SerializedName("population") val population: String,

    )
