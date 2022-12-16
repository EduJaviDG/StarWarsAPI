package com.example.mvvmrecycler.data.repository


import com.example.mvvmrecycler.domain.model.CharacterResponse
import com.example.mvvmrecycler.resource.Resource


interface ApiRepository {

    suspend fun getcharacterByName(search: String): Resource<CharacterResponse>

        //return RetrofitInstance.api.getCharacterByName(search)


}