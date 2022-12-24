package com.example.mvvmrecycler.data.api

import com.example.mvvmrecycler.domain.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

  @GET("people/")
  suspend fun getCharacterByName(@Query("search") search: String): Response<CharacterResponse>

  @GET("starships/{number}")
  suspend fun getStarship(@Path("number") starship: String): Response<StarShipResponse>

  @GET("species/{number}")
  suspend fun getSpecie(@Path("number") specie: String): Response<SpecieResponse>

  @GET("vehicles/{number}")
  suspend fun getVehicle(@Path("number") vehicle: String): Response<VehicleResponse>

  @GET("planets/{number}")
  suspend fun getPlanet(@Path("number") homeworld: String): Response<PlanetResponse>

}