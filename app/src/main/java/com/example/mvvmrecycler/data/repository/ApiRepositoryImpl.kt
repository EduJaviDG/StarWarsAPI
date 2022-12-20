package com.example.mvvmrecycler.data.repository

import android.net.wifi.hotspot2.pps.HomeSp
import com.example.mvvmrecycler.data.api.APIService
import com.example.mvvmrecycler.domain.model.*
import com.example.mvvmrecycler.resource.Resource
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val retrofit: Retrofit)  : ApiRepository {
    override suspend fun getcharacterByName(search: String): Resource<CharacterResponse> {

        return try{

            val result = retrofit.create(APIService::class.java).getCharacterByName(search)

            Resource.Success(result)


        } catch(e: Exception) {

            Resource.Error(e)

        }

    }

    override suspend fun getStarship(starship: String): Resource<StarShipResponse> {

        return try{

            val result = retrofit.create(APIService::class.java).getStarship(starship)

            Resource.Success(result)


        } catch(e: Exception) {

            Resource.Error(e)

        }


    }

    override suspend fun getSpecie(specie: String): Resource<SpecieResponse> {

        return try{

            val result = retrofit.create(APIService::class.java).getSpecie(specie)

            Resource.Success(result)


        } catch(e: Exception) {

            Resource.Error(e)

        }



    }

    override suspend fun getVehicle(vehicle: String): Resource<VehicleResponse> {

        return try{

            val result = retrofit.create(APIService::class.java).getVehicle(vehicle)

            Resource.Success(result)


        } catch(e: Exception) {

            Resource.Error(e)

        }


    }

    override suspend fun getPlanet(homeworld: String) =

        retrofit.create(APIService::class.java).getPlanet(homeworld)


}