package com.example.mvvmrecycler.data.repository

import android.content.Context
import android.util.Log
import com.example.mvvmrecycler.data.api.APIService
import com.example.mvvmrecycler.domain.model.*
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val retrofit: Retrofit) : ApiRepository {
    override suspend fun getcharacterByName(search: String): List<Character> {

       lateinit var list: List<Character>

        try{

            val result = retrofit.create(APIService::class.java).getCharacterByName(search)

            list = listOf(result.body()?.result!!.first())


        } catch(e: Exception) {

            list = emptyList()

            Log.i("MyTag", e.message.toString())


        }

        return list
    }


    override suspend fun getPlanet(homeworld: String): List<PlanetResponse>{

        lateinit var list: List<PlanetResponse>

        try{

            val result = retrofit.create(APIService::class.java).getPlanet(homeworld)

            list = listOf(result.body()!!)


        } catch(e: Exception) {

            list = emptyList()

            Log.i("MyTag", e.message.toString())

        }

        return list

    }

    override suspend fun getSpecie(specie: String): List<SpecieResponse> {

        lateinit var list: List<SpecieResponse>

        try{

            val result = retrofit.create(APIService::class.java).getSpecie(specie)

            list = listOf(result.body()!!)


        } catch(e: Exception) {

            list = emptyList()

            Log.i("MyTag", e.message.toString())


        }

        return list



    }

    override suspend fun getStarship(starship: String): List<StarShipResponse> {

        lateinit var list: List<StarShipResponse>

        try{

            val result = retrofit.create(APIService::class.java).getStarship(starship)

            list = listOf(result.body()!!)


        } catch(e: Exception) {

            list = emptyList()

            Log.i("MyTag", e.message.toString())

        }

        return list

    }

    override suspend fun getVehicle(vehicle: String): List<VehicleResponse> {

        lateinit var list: List<VehicleResponse>

        try{

            val result = retrofit.create(APIService::class.java).getVehicle(vehicle)

            list = listOf(result.body()!!)


        } catch(e: Exception) {

            list = emptyList()

            Log.i("MyTag", e.message.toString())

        }

        return list

    }


}