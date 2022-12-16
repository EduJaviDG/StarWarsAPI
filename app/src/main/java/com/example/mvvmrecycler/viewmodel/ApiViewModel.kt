package com.example.mvvmrecycler.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecycler.data.repository.ApiRepository
import com.example.mvvmrecycler.domain.usecase.apiUseCase.SearchByNameCase
import com.example.mvvmrecycler.domain.model.CharacterResponse
import com.example.mvvmrecycler.domain.model.Characters
import kotlinx.coroutines.launch
import retrofit2.Response

class ApiViewModel(private val repository: ApiRepository): ViewModel(){

    private var _character: MutableLiveData<List<Characters?>> = MutableLiveData()

    val character: LiveData<List<Characters?>> = _character

    fun searchByName(search: String) = viewModelScope.launch {

       val result: Response<CharacterResponse> = repository.getcharacterByName(search)

        if (result.isSuccessful){

            val response: CharacterResponse? = result.body()

            _character.value = response?.result ?: emptyList()

            println("RESULT == SUCCESS")

        } else {

            println("RESULT == FAIL")


        }

    }

}