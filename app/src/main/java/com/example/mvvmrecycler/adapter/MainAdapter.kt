package com.example.mvvmrecycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmrecycler.R
import com.example.mvvmrecycler.ui.model.User

class MainAdapter(private val listUser: List<User>): RecyclerView.Adapter<MainViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)

        return  MainViewHolder(layoutInflater.inflate(R.layout.item_recycler,parent,false))

    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.render(listUser[position])

    }

    override fun getItemCount(): Int {

        return listUser.size


    }


}