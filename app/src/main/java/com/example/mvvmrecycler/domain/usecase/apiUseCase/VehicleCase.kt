package com.example.mvvmrecycler.domain.usecase.apiUseCase

import com.example.mvvmrecycler.data.repository.ApiRepository
import com.example.mvvmrecycler.domain.model.VehicleResponse
import com.example.mvvmrecycler.resource.Resource
import javax.inject.Inject

class VehicleCase @Inject constructor(private val repository: ApiRepository) {

    suspend fun getVehicle(vehicle: String): Resource<VehicleResponse> =
        repository.getVehicle(vehicle)


}