package com.example.mvvmrecycler.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mvvmrecycler.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {

            setKeepOnScreenCondition{ splashViewModel.isLoading.value }

        }

        val intent = Intent(this,MainActivity::class.java)

        startActivity(intent)

    }
}