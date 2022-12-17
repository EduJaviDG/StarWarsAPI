package com.example.mvvmrecycler.data.repository


import com.example.mvvmrecycler.domain.model.*
import com.example.mvvmrecycler.resource.Resource
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiRepository {

    suspend fun getcharacterByName(search: String): Resource<CharacterResponse>


    suspend fun getStarship(starship: String): Resource<StarShipResponse>


    suspend fun getSpecie(specie: String): Resource<SpecieResponse>


    suspend fun getVehicle(vehicle: String): Resource<VehicleResponse>


    suspend fun getPlanet(homeworld: String): Resource<PlanetResponse>


}