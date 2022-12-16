package com.example.mvvmrecycler.domain.usecase.apiUseCase

import com.example.mvvmrecycler.data.repository.ApiRepository
import com.example.mvvmrecycler.domain.model.CharacterResponse
import com.example.mvvmrecycler.resource.Resource
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class SearchByNameCase @Inject constructor(private val repository: ApiRepository) {

    suspend fun  searchCase(search: String): Resource<CharacterResponse>{

       return repository.getcharacterByName(search)

    }

}