package com.example.mvvmrecycler.domain.usecase.apiUseCase

import com.example.mvvmrecycler.data.repository.ApiRepository
import com.example.mvvmrecycler.domain.model.VehicleResponse
import com.example.mvvmrecycler.resource.Resource
import retrofit2.Response
import javax.inject.Inject

class VehicleCase @Inject constructor(private val repository: ApiRepository) {

    suspend fun getVehicle(vehicle: String) = repository.getVehicle(vehicle)


}