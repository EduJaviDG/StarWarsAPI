package com.example.mvvmrecycler.domain.usecase.apiUseCase

import com.example.mvvmrecycler.data.repository.ApiRepository
import com.example.mvvmrecycler.domain.model.CharacterResponse
import retrofit2.Response

class SearchByNameCase(private val repository: ApiRepository) {

    suspend fun  searchCase(search: String): Response<CharacterResponse> {

       return repository.getcharacterByName(search)

    }

}