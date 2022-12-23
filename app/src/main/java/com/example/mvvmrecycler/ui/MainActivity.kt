package com.example.mvvmrecycler.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmrecycler.R
import com.example.mvvmrecycler.adapter.StarWarsAdapter
import com.example.mvvmrecycler.databinding.ActivityMainBinding
import com.example.mvvmrecycler.domain.model.Alert
import com.example.mvvmrecycler.domain.model.CharacterCard
import com.example.mvvmrecycler.resource.Resource
import com.example.mvvmrecycler.viewmodel.ApiViewModel
import com.example.mvvmrecycler.viewmodel.SplashViewModel
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

        setContentView(binding.root)

        supportActionBar?.hide()

        binding.svSearch.setOnQueryTextListener(this)

        initRecycler()


    }

    private fun initRecycler(){

        Alert.showWelcome(this)

        adapter = StarWarsAdapter(listCharacter){ character,position ->

                onClickListener(character,position)

        }

        val manager = LinearLayoutManager(this)

        val recycler = binding.recycler

        recycler.layoutManager = manager

        recycler.adapter = adapter

    }

    private fun onClickListener(character:CharacterCard, position: Int){

        character.isExnpaded = true

        adapter.notifyItemChanged(position)

    }

    private fun searchByName(name: String){

        val reName = name.lowercase()

        lifecycleScope.launchWhenStarted {

            apiViewModel.searchByName(reName)

            val response = apiViewModel.character

            response.collect{ action ->

                when (action) {

                    is Resource.Error -> {

                        Alert.showError(this@MainActivity)

                    }

                    is Resource.Success -> {

                        showShimmer()

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

                                    Handler(Looper.getMainLooper()).postDelayed({

                                        showData()

                                    },3000)

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

    private fun showShimmer(){

        binding.shimmerViewContainer.visibility = View.VISIBLE

        binding.recycler.isVisible= false


    }

    private fun showData(){

        binding.shimmerViewContainer.visibility = View.GONE

        binding.recycler.isVisible = true

    }


}

