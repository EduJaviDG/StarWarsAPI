package com.example.mvvmrecycler.domain.usecase.apiUseCase

import com.example.mvvmrecycler.data.repository.ApiRepository
import com.example.mvvmrecycler.domain.model.StarShip
import com.example.mvvmrecycler.domain.model.StarShipResponse
import retrofit2.Response
import javax.inject.Inject

class StarShipCase @Inject constructor(private val repository: ApiRepository) {

    suspend fun getStarShip(starShip: String) = repository.getStarship(starShip)

}