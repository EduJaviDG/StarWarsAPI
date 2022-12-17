package com.example.mvvmrecycler.util

 enum class Constants(private val value: String) {

     BASE_URL ("https://swapi.dev/api/"),

     KEY_PLANET("planet"),

     KEY_VEHICLE("vehicle"),

     KEY_STARSHIP("starShip"),

     KEY_SPECIE("specie");

     override fun toString(): String  = value


}