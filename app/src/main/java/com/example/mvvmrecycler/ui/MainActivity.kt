package com.example.mvvmrecycler.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmrecycler.adapter.MainAdapter
import com.example.mvvmrecycler.databinding.ActivityMainBinding
import com.example.mvvmrecycler.ui.model.User
import com.example.mvvmrecycler.viewmodel.MyViewModel
import com.facebook.shimmer.Shimmer
import com.google.firebase.FirebaseApp


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MyViewModel
    private var usersList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get()

        initRecycler()

        observeData()

    }

    private fun initRecycler() {

        adapter = MainAdapter(usersList)

        val manager = LinearLayoutManager(this)

        val recyclerView = binding.recycler

        recyclerView.layoutManager = manager

        recyclerView.adapter = adapter

    }

    private fun observeData() {

        binding.shimmerViewContainer.startShimmer()

        viewModel.listData.observe(this, Observer {

            binding.shimmerViewContainer.stopShimmer()

            binding.shimmerViewContainer.hideShimmer()

            usersList.addAll(it)

            adapter.notifyDataSetChanged()

        })

    }

}