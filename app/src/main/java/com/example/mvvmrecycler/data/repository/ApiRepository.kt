package com.example.mvvmrecycler.data.repository

import com.example.mvvmrecycler.data.api.RetrofitInstance
import com.example.mvvmrecycler.domain.model.CharacterResponse
import retrofit2.Response

class ApiRepository {

    suspend fun getcharacterByName(search: String): Response<CharacterResponse> {

        return RetrofitInstance.api.getCharacterByName(search)

    }
}