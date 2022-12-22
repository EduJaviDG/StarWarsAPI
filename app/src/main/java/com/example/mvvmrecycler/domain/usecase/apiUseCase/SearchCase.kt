package com.example.mvvmrecycler.domain.usecase.apiUseCase

import com.example.mvvmrecycler.data.repository.ApiRepository
import com.example.mvvmrecycler.domain.model.CharacterResponse
import com.example.mvvmrecycler.resource.Resource
import javax.inject.Inject

class SearchCase @Inject constructor(private val repository: ApiRepository) {

    suspend fun searchByName(search: String) = repository.getcharacterByName(search)

}