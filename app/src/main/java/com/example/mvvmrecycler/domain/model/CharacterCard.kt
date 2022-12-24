package com.example.mvvmrecycler.domain.model

import com.example.mvvmrecycler.R
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
    val specie: String = "Human",
    val language: String = "Galactic Basic",
    var isExnpaded: Boolean = false

    )