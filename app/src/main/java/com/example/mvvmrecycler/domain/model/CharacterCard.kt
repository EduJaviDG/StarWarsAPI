package com.example.mvvmrecycler.domain.model

import com.google.gson.annotations.SerializedName

data class CharacterCard(

    val name: String,
    val height: String,
    val mass: String,
    val hair: String,
    val skin: String,
    val eye: String,
    val birth: String,
    val gender: String,
    val homeworld: String,
    var isExnpaded: Boolean = false

    )