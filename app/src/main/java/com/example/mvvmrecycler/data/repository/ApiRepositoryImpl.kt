package com.example.mvvmrecycler.data.repository

import com.example.mvvmrecycler.data.api.APIService
import com.example.mvvmrecycler.domain.model.CharacterResponse
import com.example.mvvmrecycler.resource.Resource
import retrofit2.Retrofit
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val retrofit: Retrofit)  : ApiRepository {
    override suspend fun getcharacterByName(search: String): Resource<CharacterResponse> {

        return try{

            val result = retrofit.create(APIService::class.java).getCharacterByName(search)

            Resource.Success(result)

        } catch(e: Exception){

            Resource.Error(e)


        }


    }
}