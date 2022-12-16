package com.example.mvvmrecycler.domain.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(

    @SerializedName("count") var count: Int,
    @SerializedName("next")  var next: String?,
    @SerializedName("previous") var previous: String?,
    @SerializedName("results") var result : List<Characters>,

    )

