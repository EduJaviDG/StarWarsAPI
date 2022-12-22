package com.example.mvvmrecycler.domain.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(

    @SerializedName("count") val count: Int,
    @SerializedName("next")  val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val result : List<Character>,

    )

