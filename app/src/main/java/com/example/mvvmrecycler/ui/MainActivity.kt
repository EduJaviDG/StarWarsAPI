package com.example.mvvmrecycler.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmrecycler.adapter.StarWarsAdapter
import com.example.mvvmrecycler.databinding.ActivityMainBinding
import com.example.mvvmrecycler.domain.model.*
import com.example.mvvmrecycler.resource.Resource
import com.example.mvvmrecycler.util.Constants
import com.example.mvvmrecycler.viewmodel.ApiViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StarWarsAdapter
    private var listCharacter = mutableListOf<CharacterCard>()
    private val apiViewModel by viewModels<ApiViewModel>()
    private val mapAttribute = mutableMapOf<String,String>()
    private val listVehicle = mutableListOf<String>()
    private val listStarShip = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        initRecycler()


    }

    private fun initRecycler(){

        adapter = StarWarsAdapter(listCharacter)

        val manager = LinearLayoutManager(this)

        val recycler = binding.recycler

        recycler.layoutManager = manager

        recycler.adapter = adapter

    }

    private fun inflateRecycler() {

       val character = CharacterCard(
           name = mapAttribute["name"].toString() ,
           height = mapAttribute["height"].toString(),
           mass = mapAttribute["mass"].toString() ,
           hair = mapAttribute["hair"].toString() ,
           skin = mapAttribute["skin"].toString(),
           eye = mapAttribute["eye"].toString(),
           birth = mapAttribute["birth"].toString(),
           gender = mapAttribute["gender"].toString(),
           homeworld = mapAttribute["planet"].toString(),
           specie = mapAttribute["specie"].toString(),
           language = mapAttribute["language"].toString(),
           vehicles = listVehicle,
           starShips = listStarShip
       )

        listCharacter.clear()

        listCharacter.add(character)

        adapter.notifyDataSetChanged()

    }
    private fun searchByName(name: String){

        apiViewModel.searchByName(name)

        val response = apiViewModel.character

        response.observe(this) { action ->

            when (action) {

                is Resource.Error -> {

                    Log.d("Response", "ERROR")

                }

                is Resource.Success -> {

                    val data = action.result.result

                    mapAttribute.put("name",data.first().name)

                    mapAttribute.put("height",data.first().height)

                    mapAttribute.put("mass",data.first().mass)

                    mapAttribute.put("skin",data.first().skin)

                    mapAttribute.put("hair",data.first().hair)

                    mapAttribute.put("eye",data.first().eye)

                    mapAttribute.put("birth",data.first().birth)

                    mapAttribute.put("gender",data.first().gender)

                    mapAttribute.put("planet",data.first().homeworld)

                    Log.d("RESPONSE", data.first().name)
                    Log.d("RESPONSE", data.first().gender)
                    Log.d("RESPONSE", data.first().birth)
                    Log.d("RESPONSE", data.first().height)
                    Log.d("RESPONSE", data.first().mass)
                    Log.d("RESPONSE", data.first().eye)
                    Log.d("RESPONSE", data.first().hair)
                    Log.d("RESPONSE", data.first().skin)

                    val idPlanet = data.first().homeworld.filter { it.isDigit() }

                    getPlanet(idPlanet)

                    val checkSpecie = data.first().species

                    if( !checkSpecie.isNullOrEmpty() ){

                        checkSpecie.forEach { url ->

                            val id = url?.filter { it.isDigit() }

                            getSpecie(id!!)

                        }

                    }

                    val checkVehicle = data.first().vehicles

                    if( !checkVehicle.isNullOrEmpty() ){

                        var countVehicle = 0

                        checkVehicle.forEach { url ->

                            val id = url.filter { it.isDigit() }

                            getVehicle(id, countVehicle)

                            countVehicle++

                        }

                    }

                    val checkStarShip = data.first().starships

                    if( !checkStarShip.isNullOrEmpty() ){

                        var countStarShip = 0

                        checkStarShip.forEach { url ->

                            val id = url.filter { it.isDigit() }

                            getStarShip(id, countStarShip)

                            countStarShip++

                        }

                    }

                }

                Resource.inProgress -> {

                    Log.d("RESPONSE", "LOADING")

                }
            }
        }



    }

    private fun getPlanet(id: String){

        apiViewModel.getPlanet(id)

        val responsePlanet = apiViewModel.planet


        responsePlanet.observe(this){ action ->

            when (action){

                is Resource.Error -> { Log.d("RESPONSE_PLANET", "ERROR") }

                is Resource.Success -> {

                    mapAttribute.put("planet",action.result.name)


                    Log.d("RESPONSE_PLANET", action.result.name)

                }

                Resource.inProgress -> { Log.d("RESPONSE_PLANET", "PROGRESS") }

            }

        }


    }

    private fun getVehicle(id: String, count: Int) {

        apiViewModel.getVehicle(id)

        val response = apiViewModel.vehicle

        response.observe(this){ action ->

            when(action){
                is Resource.Error -> { Log.d("RESPONSE_VEHICLE", "ERROR" ) }

                is Resource.Success -> {

                    listVehicle.add(action.result.name)

                    Log.d("RESPONSE_VEHICLE", action.result.name )

                }

                Resource.inProgress -> { Log.d("RESPONSE_VEHICLE", "LOADING" ) }
            }

        }
    }

    private fun getStarShip(id: String, count: Int) {

        apiViewModel.getStarShip(id)

        val response = apiViewModel.starShip

        response.observe(this){ action ->

            when(action){

                is Resource.Error -> { Log.d("RESPONSE_STARSHIP", "ERROR" ) }

                is Resource.Success -> {

                    listStarShip.add(action.result.name)

                    Log.d("RESPONSE_STARSHIP", action.result.name )

                }

                Resource.inProgress -> { Log.d("RESPONSE_STARSHIP", "LOADING" ) }

            }

        }

    }

    private fun getSpecie(id: String){

        apiViewModel.getSpecie(id)

        val response = apiViewModel.specie

        response.observe(this){ action ->

            when(action){

                is Resource.Error -> { Log.d("RESPONSE_STARSHIP", "ERROR" ) }

                is Resource.Success -> {

                    mapAttribute.put("specie", action.result.name)

                    mapAttribute.put("language", action.result.language)

                    Log.d("RESPONSE_STARSHIP", action.result.name)

                }

                Resource.inProgress -> { Log.d("RESPONSE_STARSHIP", "LOADING" ) }

            }

        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

    if(!query.isNullOrEmpty()) {

        searchByName(query!!)

        inflateRecycler()

    }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        TODO("Not yet implemented")
    }


}

