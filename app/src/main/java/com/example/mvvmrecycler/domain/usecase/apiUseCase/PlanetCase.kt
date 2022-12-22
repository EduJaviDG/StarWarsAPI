package com.example.mvvmrecycler.domain.usecase.apiUseCase

import com.example.mvvmrecycler.data.repository.ApiRepository
import com.example.mvvmrecycler.domain.model.PlanetResponse
import retrofit2.Response
import javax.inject.Inject

class PlanetCase @Inject constructor(private val repository: ApiRepository) {

    suspend fun getPlanet(planet: String) = repository.getPlanet(planet)


}