package com.example.mvvmrecycler.domain.usecase.apiUseCase
import com.example.mvvmrecycler.data.repository.ApiRepository
import javax.inject.Inject

class SpecieCase @Inject constructor(private val repository: ApiRepository) {

    suspend fun getSpecie(specie: String) = repository.getSpecie(specie)

}