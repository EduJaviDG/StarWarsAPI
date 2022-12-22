package com.example.mvvmrecycler.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmrecycler.adapter.StarWarsAdapter
import com.example.mvvmrecycler.databinding.ActivityMainBinding
import com.example.mvvmrecycler.domain.model.CharacterCard
import com.example.mvvmrecycler.resource.Resource
import com.example.mvvmrecycler.viewmodel.ApiViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flatMapConcat


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StarWarsAdapter
    private var listCharacter = mutableListOf<CharacterCard>()
    private val apiViewModel by viewModels<ApiViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        supportActionBar?.hide()

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

                        val id = data.first().homeworld.filter { it.isDigit() }

                        response.flatMapConcat { response ->

                            apiViewModel.getPlanet(id)

                            return@flatMapConcat apiViewModel.planet

                        }.collect{ action ->

                            when(action){

                                is Resource.Error -> { Log.d("Planet", "ERROR") }
                                is Resource.Success -> {

                                    val planet = action.result

                                    val character = CharacterCard(

                                        name = data.first().name,
                                        height = data.first().height,
                                        mass = data.first().mass,
                                        hair = data.first().hair ,
                                        skin = data.first().skin,
                                        eye = data.first().eye,
                                        birth = data.first().birth,
                                        gender = data.first().gender,
                                        homeworld = planet.name,
                                    )

                                    listCharacter.clear()

                                    listCharacter.add(character)

                                    adapter.notifyDataSetChanged()

                                }
                                Resource.inProgress -> {Log.d("Planet", "LOADING")}
                            }
                        }
                    }

                    Resource.inProgress -> {

                        Log.d("RESPONSE", "LOADING")

                    }
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        if(query != null) {

            searchByName(query)

        }

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return false

    }


}

