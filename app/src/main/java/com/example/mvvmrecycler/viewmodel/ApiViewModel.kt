package com.example.mvvmrecycler.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecycler.domain.model.*
import com.example.mvvmrecycler.domain.usecase.apiUseCase.*
import com.example.mvvmrecycler.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(

    private val searchCase: SearchCase,
    private val planetCase: PlanetCase,
    private val specieCase: SpecieCase,

): ViewModel() {

    private var _character = MutableStateFlow<List<Character>?>(null)

    val character: StateFlow<List<Character>?> = _character

    private var _planet = MutableStateFlow<List<PlanetResponse>?>(null)

    val planet: StateFlow<List<PlanetResponse>?> = _planet

    private var _specie = MutableStateFlow<List<SpecieResponse>?>(null)

    val specie: StateFlow<List<SpecieResponse>?> = _specie



    fun searchByName(search: String) = viewModelScope.launch {

        val result = withContext(Dispatchers.IO){searchCase.searchByName(search)}

        _character.value = result


        Log.i("Response", _character.value.toString())

    }

    fun getPlanet(planet: String) = viewModelScope.launch {

        val result = withContext(Dispatchers.IO){ planetCase.getPlanet(planet) }

        _planet.value = result

    }

    fun getSpecie(specie: String) = viewModelScope.launch {

        val result = withContext(Dispatchers.IO){ specieCase.getSpecie(specie) }

        _specie.value = result

    }


}