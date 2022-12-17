package com.example.mvvmrecycler.data.api

import com.example.mvvmrecycler.domain.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

  @GET("people/")
  suspend fun getCharacterByName(@Query("search") search: String): CharacterResponse

  @GET("starship/{number}")
  suspend fun getStarship(@Path("number") starship: String): StarShipResponse

  @GET("specie/{number}")
  suspend fun getSpecie(@Path("number") specie: String): SpecieResponse

  @GET("vehicle/{number}")
  suspend fun getVehicle(@Path("number") vehicle: String): VehicleResponse

  @GET("homeworld/{number}")
  suspend fun getPlanet(@Path("number") homeworld: String): PlanetResponse

}