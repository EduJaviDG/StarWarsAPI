package com.example.mvvmrecycler.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mvvmrecycler.databinding.ActivityMainBinding
import com.example.mvvmrecycler.domain.model.CharacterResponse
import com.example.mvvmrecycler.domain.model.Shared
import com.example.mvvmrecycler.resource.Resource
import com.example.mvvmrecycler.util.Constants
import com.example.mvvmrecycler.viewmodel.ApiViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiViewModel by viewModels<ApiViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        apiViewModel.searchByName("luke")

        val response = apiViewModel.character

        response.observe(this) { action ->

            when (action) {

                is Resource.Error -> {

                    Log.d("Response", "ERROR")

                }

                is Resource.Success -> {

                    val data = action.result.result

                        Log.d("Response", data.first().name)

                        Log.d("Response", data.first().gender)

                        Log.d("Response", data.first().birth)

                        Log.d("Response", data.first().height)

                        Log.d("Response", data.first().mass)

                        Log.d("Response", data.first().eye)

                        Log.d("Response", data.first().hair)

                        Log.d("Response", data.first().skin)

                    val planet = data.first().homeworld.takeLast(2)

                    val sharedPlanet = Shared(
                        this@MainActivity,
                        Constants.KEY_PLANET.toString()
                    )

                    sharedPlanet.putString(planet)

                    var counter = 0

                    data.first().vehicles.forEach { vehicle ->

                        val sharedVehicles = Shared(
                            this@MainActivity,
                            "${Constants.KEY_VEHICLE}${counter}"
                        )

                        sharedVehicles.putString(vehicle)

                        counter++

                    }

                    data?.first()!!.starships.forEach { starShip ->

                        counter = 0

                        val sharedStarShip = Shared(
                            this@MainActivity,
                            "${Constants.KEY_STARSHIP}${counter}"
                        )

                        sharedStarShip.putString(starShip)

                        counter++

                    }

                    val specie = data?.first()!!.species.first().toString().takeLast(2)

                    val sharedSpecie = Shared(
                        this@MainActivity,
                        Constants.KEY_SPECIE.toString()
                    )

                    sharedSpecie.putString(specie)

                }

                Resource.inProgress -> {

                    Log.d("Response", "LOADING")

                }

            }

        }

        val shared = Shared(this, Constants.KEY_PLANET.toString())

        val dataPlanet = shared.getString(Constants.KEY_PLANET.toString(),"")

        apiViewModel.getPlanet(dataPlanet.toString())

        val responsePlanet = apiViewModel.planet


        responsePlanet.observe(this){ action ->

            when (action){

                is Resource.Error -> {

                    Log.d("PLANET", "ERROR")

                }
                is Resource.Success -> {

                    Log.d("PLANET", action.result.name)

                }
                Resource.inProgress -> {

                    Log.d("PLANET", "PROGRESS")

                }
            }

        }

    }

}

