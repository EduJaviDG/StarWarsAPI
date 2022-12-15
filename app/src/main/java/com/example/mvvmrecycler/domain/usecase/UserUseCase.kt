package com.example.mvvmrecycler.domain.usecase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mvvmrecycler.data.UserDataSet
import com.example.mvvmrecycler.ui.model.User
import kotlinx.coroutines.coroutineScope

class UserUseCase {

    fun getListOfUsers(): MutableLiveData<MutableList<User>> {

        val userData = UserDataSet()

         return userData.getUserData()

    }


}