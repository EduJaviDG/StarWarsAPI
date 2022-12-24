package com.example.mvvmrecycler.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmrecycler.adapter.StarWarsAdapter
import com.example.mvvmrecycler.databinding.ActivityMainBinding
import com.example.mvvmrecycler.domain.model.Alert
import com.example.mvvmrecycler.domain.model.CharacterCard
import com.example.mvvmrecycler.viewmodel.ApiViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
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

    fun updateRecycler(character: CharacterCard){

        listCharacter.clear()

        listCharacter.add(character)

        adapter.notifyDataSetChanged()

        Handler(mainLooper).postDelayed({

            showData()

        },4500)


    }

    private fun onClickListener(character:CharacterCard, position: Int){

        character.isExnpaded = true

        adapter.notifyItemChanged(position)

    }

    private fun searchByName(name: String) {

        lifecycleScope.launchWhenStarted {

            apiViewModel.searchByName(name.lowercase())

            delay(1500)
            val response = apiViewModel.character
            Log.i("Main", response.value.toString())

            if (response.value == null) {

                Alert.showConnect(this@MainActivity)

                lifecycleScope.coroutineContext.cancelChildren()

            }

            response.collect { list ->

                when {

                    list?.isNotEmpty()!! -> {

                        showShimmer()

                        val data = list.first()

                        val idPlanet = data.homeworld.filter { it.isDigit() }

                        response.flatMapConcat { response ->

                            apiViewModel.getPlanet(idPlanet)

                            delay(1000)
                            return@flatMapConcat apiViewModel.planet

                        }.collect { list ->

                            val planet = list?.first()

                            val listSpecie: List<String> =
                                data.species.ifEmpty { listOf("00", "00") }

                            val idSpecie = listSpecie.first().filter { it.isDigit() }

                            response.flatMapConcat { response ->

                                apiViewModel.getSpecie(idSpecie)

                                delay(1000)
                                return@flatMapConcat apiViewModel.specie

                            }.collect { list ->

                                if (list?.isNotEmpty()!!) {

                                    val specie = list.first()

                                    val character = CharacterCard(

                                        name = data.name,
                                        height = data.height,
                                        mass = data.mass,
                                        hair = data.hair,
                                        skin = data.skin,
                                        eye = data.eye,
                                        birth = data.birth,
                                        gender = data.gender,
                                        homeworld = planet?.name!!,
                                        specie = specie.name,
                                        language = specie.language

                                    )

                                    updateRecycler(character)


                                } else {

                                    val character = CharacterCard(

                                        name = data.name,
                                        height = data.height,
                                        mass = data.mass,
                                        hair = data.hair,
                                        skin = data.skin,
                                        eye = data.eye,
                                        birth = data.birth,
                                        gender = data.gender,
                                        homeworld = planet?.name!!,

                                        )

                                    updateRecycler(character)

                                }

                                lifecycleScope.coroutineContext.cancelChildren()
                            }
                        }

                    }

                    list.isEmpty() -> {

                        Alert.showError(this@MainActivity)

                        lifecycleScope.coroutineContext.cancelChildren()

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

        binding.recycler.isVisible = false


    }

    private fun showData(){

        binding.shimmerViewContainer.visibility = View.GONE

        binding.recycler.isVisible = true

    }


}

