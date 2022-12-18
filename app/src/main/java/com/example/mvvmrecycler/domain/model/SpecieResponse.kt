package com.example.mvvmrecycler.domain.model

import com.google.gson.annotations.SerializedName

data class SpecieResponse(

    @SerializedName("name") val name: String,
    @SerializedName("language")  val language: String,

)