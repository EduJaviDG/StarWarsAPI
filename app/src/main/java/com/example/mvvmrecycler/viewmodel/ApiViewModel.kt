package com.example.mvvmrecycler.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecycler.domain.model.*
import com.example.mvvmrecycler.domain.usecase.apiUseCase.*
import com.example.mvvmrecycler.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(

    private val searchCase: SearchCase,
    private val planetCase: PlanetCase,
    private val vehicleCase: VehicleCase,
    private val starShipCase: StarShipCase,
    private val specieCase: SpecieCase,

): ViewModel() {

    private var _character = MutableLiveData<Resource<CharacterResponse>>(null)

    val character: LiveData<Resource<CharacterResponse>> = _character

    private var _planet = MutableLiveData<Resource<PlanetResponse>>(null)

    val planet: LiveData<Resource<PlanetResponse>> = _planet

    private var _vehicle = MutableLiveData<Resource<VehicleResponse>>(null)

    val vehicle: LiveData<Resource<VehicleResponse>> = _vehicle

    private var _starShip = MutableLiveData<Resource<StarShipResponse>>(null)

    val starShip: LiveData<Resource<StarShipResponse>> = _starShip

    private var _specie = MutableLiveData<Resource<SpecieResponse>>(null)

    val specie: LiveData<Resource<SpecieResponse>> = _specie

    fun searchByName(search: String) = viewModelScope.launch {

        _character.value = Resource.inProgress

        val result = searchCase.searchByName(search)

        _character.value = result

    }

    fun getVehicle(vehicle: String) = viewModelScope.launch {

        _vehicle.value = Resource.inProgress

        val result = vehicleCase.getVehicle(vehicle)

        _vehicle.value = result


    }

    fun getStarShip(starShip: String) = viewModelScope.launch {

        _starShip.value = Resource.inProgress

        val result = starShipCase.getStarShip(starShip)

        _starShip.value = result


    }

    fun getPlanet(planet: String) = viewModelScope.launch {

        _planet.value = Resource.inProgress

        val result = planetCase.getPlanet(planet)

        _planet.value = result


    }


    fun getSpecie(specie: String) = viewModelScope.launch {

        _specie.value = Resource.inProgress

        val result = specieCase.getSpecie(specie)

        _specie.value = result

    }


}