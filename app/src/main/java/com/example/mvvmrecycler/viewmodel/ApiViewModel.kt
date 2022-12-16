package com.example.mvvmrecycler.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecycler.domain.model.CharacterResponse
import com.example.mvvmrecycler.domain.usecase.apiUseCase.SearchByNameCase
import com.example.mvvmrecycler.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(private val serachUse: SearchByNameCase): ViewModel() {

    private var _character = MutableLiveData<Resource<CharacterResponse>>(null)

    val character: LiveData<Resource<CharacterResponse>> = _character

    fun searchByName(search: String) = viewModelScope.launch {

        _character.value = Resource.inProgress

        val result = serachUse.searchCase(search)

        _character.value = result

    }

}