package com.example.mvvmrecycler.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mvvmrecycler.databinding.ActivityMainBinding
import com.example.mvvmrecycler.domain.model.CharacterResponse
import com.example.mvvmrecycler.resource.Resource
import com.example.mvvmrecycler.viewmodel.ApiViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiViewModel by viewModels<ApiViewModel>()
    private val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        apiViewModel.searchByName("luke")

        val response = apiViewModel.character

        response.observe(this, object: Observer<Resource<CharacterResponse>>{
            override fun onChanged(action: Resource<CharacterResponse>?) {

                when(action){

                    is Resource.Error -> {

                        Log.d("Response","ERROR")

                    }

                    is Resource.Success -> {

                        val data = action.result.result

                        Log.d("Response", data?.first()!!.name)

                        Log.d("Response", data?.first()!!.gender)

                        Log.d("Response", data?.first()!!.birth )

                        Log.d("Response", data?.first()!!.height)

                        Log.d("Response", data?.first()!!.mass)

                        Log.d("Response", data?.first()!!.eye)

                        Log.d("Response", data?.first()!!.hair)

                        Log.d("Response", data?.first()!!.skin)

                    }
                    Resource.inProgress -> {

                        Log.d("Response", "LOADING")

                    }
                    null -> TODO()
                }


            }


        })

        //val repository = ApiRepository()

        //val viewModelFactory = ApiViewModelFactory(repository)

        //viewModel = ViewModelProvider(this, viewModelFactory).get(ApiViewModel::class.java)

        //viewModel.searchByName(search = "luke")

        /*viewModel.character.observe(this){ data ->

            Log.d("Response", data?.first()!!.name)

            Log.d("Response", data?.first()!!.gender)

            Log.d("Response", data?.first()!!.birth )

            Log.d("Response", data?.first()!!.height)

            Log.d("Response", data?.first()!!.mass)

            Log.d("Response", data?.first()!!.eye)

            Log.d("Response", data?.first()!!.hair)

            Log.d("Response", data?.first()!!.skin)

        }*/

    }

}