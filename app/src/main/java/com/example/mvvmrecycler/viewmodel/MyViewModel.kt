package com.example.mvvmrecycler.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecycler.data.UserDataSet
import com.example.mvvmrecycler.domain.usecase.UserUseCase
import com.example.mvvmrecycler.ui.model.User
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    private val usertUseCase = UserUseCase()

    private val _listData = MutableLiveData<List<User>>()

    val listData: LiveData<List<User>> get() = _listData

    init {

        getListOfUsersFinal()

    }

    private fun setListData(listOfUsers: List<User>) {

        _listData.value = listOfUsers

    }

    private fun getListOfUsersFinal() {

        viewModelScope.launch {

            val mutableListUsers = mutableListOf<User>()

            usertUseCase.getListOfUsers().observeForever {  list ->

                var listUsers = listOf<User>()

                list.forEach{

                    mutableListUsers.add(it)

                }

                listUsers = mutableListUsers

                setListData(listUsers)

            }

        }

    }

}