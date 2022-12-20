package com.example.mvvmrecycler.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmrecycler.adapter.StarWarsAdapter
import com.example.mvvmrecycler.databinding.ActivityMainBinding
import com.example.mvvmrecycler.domain.model.*
import com.example.mvvmrecycler.resource.Resource
import com.example.mvvmrecycler.viewmodel.ApiViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StarWarsAdapter
    private var listCharacter = mutableListOf<CharacterCard>()
    private val apiViewModel by viewModels<ApiViewModel>()
    private lateinit var shared: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        shared = this.getSharedPreferences("search_name", Context.MODE_PRIVATE)

        binding.svSearch.setOnQueryTextListener(this)

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

           name = shared.getString("name","n/a").toString(),
           height = shared.getString("height","n/a").toString(),
           mass = shared.getString("mass","n/a").toString() ,
           hair = shared.getString("hair","n/a").toString() ,
           skin = shared.getString("skin","n/a").toString(),
           eye = shared.getString("eye","n/a").toString(),
           birth = shared.getString("birth","n/a").toString(),
           gender = shared.getString("gender","n/a").toString(),
           //homeworld = shared.getString("planet","n/a").toString(),
           //specie = shared.getString("specie_name","n/a").toString(),
           //language = shared.getString("specie_language","n/a").toString(),
           //vehicles = "${shared.getString("vehicle0","n/a")},\n" +
                   //"${shared.getString("vehicle1","n/a")}",
           //starShips = "${shared.getString("starship0","n/a")},\n" +
                   //"${shared.getString("starship1","n/a")}"
       )

        shared.edit().clear().apply()

        listCharacter.clear()

        listCharacter.add(character)

        adapter.notifyDataSetChanged()

    }

    private fun searchByName(name: String){

        lifecycleScope.launchWhenStarted {
            apiViewModel.searchByName(name)

            val response = apiViewModel.character

            response.collect{ action ->

                when (action) {

                    is Resource.Error -> {

                        Log.d("Response", "ERROR")

                    }

                    is Resource.Success -> {

                        val data = action.result.result

                        val idPlanet = data.first().homeworld

                        val character = CharacterCard(

                            name = data.first().name,
                            height =  data.first().height,
                            mass = data.first().mass,
                            hair = data.first().skin,
                            skin = data.first().hair,
                            eye = data.first().eye,
                            birth = data.first().birth,
                            gender = data.first().gender,)

                        listCharacter.clear()

                        listCharacter.add(character)

                        adapter.notifyDataSetChanged()

                    }

                    Resource.inProgress -> {

                        Log.d("RESPONSE", "LOADING")

                    }
                }
            }
        }
    }

    private fun getPlanet(id: String){

        apiViewModel.getPlanet(id)

        val response = apiViewModel.planet

        response.observe(this, object: Observer<Resource<PlanetResponse>>{
            override fun onChanged(action: Resource<PlanetResponse>?) {

                when(action){
                    is Resource.Error -> {  Log.d("Planet", "ERROR") }
                    is Resource.Success -> {

                        with(shared.edit()){

                            putString("planet", action.result.name)

                            apply()
                        }

                        Log.d("Planet", action.result.name)

                    }
                    Resource.inProgress -> { Log.d("Planet", "LOADING")}
                }
            }
        })
    }

    private fun getVehicle(listId: List<String>) {

        var countVehicle = 0

        listId.forEach{ id ->

            apiViewModel.getVehicle(id)

            val response = apiViewModel.vehicle

            response.observe(this, object: Observer<Resource<VehicleResponse>> {
                override fun onChanged(action: Resource<VehicleResponse>?) {

                    when(action){

                        is Resource.Error -> { Log.d("Vehicle", "ERROR")  }

                        is Resource.Success -> {

                            with(shared.edit()){

                                putString("vehicle$countVehicle", action.result.name)

                                apply()
                            }

                            countVehicle++

                            response.removeObserver(this)
                        }

                        Resource.inProgress -> { Log.d("Vehicle", "LOADING") }

                    }

                }
            })
        }
    }

    private fun getStarShip(listId: List<String>) {

        var countStarShip = 0

        listId.forEach{ id ->

            apiViewModel.getStarShip(id)

            val response = apiViewModel.starShip

            response.observe(this, object : Observer<Resource<StarShipResponse>> {
                override fun onChanged(action: Resource<StarShipResponse>?) {

                    when (action) {
                        is Resource.Error -> { Log.d("Starship", "ERROR")}
                        is Resource.Success -> {

                            Log.d("StarShip_Key", "starship$countStarShip")

                            Log.d("StarShip_Name", action.result.name)

                            with(shared.edit()){

                                putString("starship$countStarShip", action.result.name)

                                apply()
                            }

                            countStarShip++

                            response.removeObserver(this)

                        }
                        Resource.inProgress -> {Log.d("Starship", "LOADING")}

                    }


                }

            })

        }

    }

    private fun getSpecie(id: String){

        apiViewModel.getSpecie(id)

        val response = apiViewModel.specie

        response.observe(this, object: Observer<Resource<SpecieResponse>> {
            override fun onChanged(action: Resource<SpecieResponse>?) {

                when (action) {
                    is Resource.Error -> { Log.d("Specie", "ERROR")}
                    is Resource.Success -> {

                        with(shared.edit()){

                            putString("specie_name", action.result.name)

                            putString("specie_language", action.result.language)

                            apply()
                        }

                        //response.removeObserver(this)
                    }
                    Resource.inProgress -> {Log.d("Specie", "LOADING")}

                }
            }

        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        if(query != null) {

            searchByName(query)

            //inflateRecycler()

        }

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return false

    }


}

