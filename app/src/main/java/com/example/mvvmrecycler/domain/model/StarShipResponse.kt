package com.example.mvvmrecycler.domain.model

import com.google.gson.annotations.SerializedName

data class StarShipResponse(

    @SerializedName("name") val name: String,
    @SerializedName("model") val model: String,
    @SerializedName("manufacture") val manufacturer: String,

    )