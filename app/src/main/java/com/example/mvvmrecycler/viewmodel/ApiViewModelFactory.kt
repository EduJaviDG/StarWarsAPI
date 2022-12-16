package com.example.mvvmrecycler.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmrecycler.data.repository.ApiRepository
import com.example.mvvmrecycler.domain.usecase.apiUseCase.SearchByNameCase

class ApiViewModelFactory (private val repository: ApiRepository): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return ApiViewModel(repository) as T

    }


}