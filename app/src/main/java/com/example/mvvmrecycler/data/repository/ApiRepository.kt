package com.example.mvvmrecycler.data.repository


import android.content.Context
import com.example.mvvmrecycler.domain.model.*
import com.example.mvvmrecycler.resource.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiRepository {

    suspend fun getcharacterByName(search: String): List<Character>

    suspend fun getStarship(starship:String): List<StarShipResponse>

    suspend fun getSpecie(specie: String): List<SpecieResponse>

    suspend fun getVehicle(vehicle: String): List<VehicleResponse>

    suspend fun getPlanet(homeworld: String): List<PlanetResponse>


}