package com.example.mvvmrecycler.viewmodel

import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.logging.Handler


class SplashViewModel(): ViewModel() {

    private var _isLoading = MutableStateFlow(true)

    val isLoading: StateFlow<Boolean> = _isLoading

    init {

        viewModelScope.launch {

            delay(2000)

            _isLoading.value = false

        }

    }

}