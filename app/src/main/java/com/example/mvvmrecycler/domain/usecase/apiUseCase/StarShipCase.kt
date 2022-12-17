package com.example.mvvmrecycler.domain.usecase.apiUseCase

import com.example.mvvmrecycler.data.repository.ApiRepository
import com.example.mvvmrecycler.domain.model.StarShip
import javax.inject.Inject

class StarShipCase @Inject constructor(private val repository: ApiRepository) {

    suspend fun getStarShip(starShip: String) = repository.getStarship(starShip)

}