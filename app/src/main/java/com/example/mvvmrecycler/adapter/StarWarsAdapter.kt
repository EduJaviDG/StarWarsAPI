package com.example.mvvmrecycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmrecycler.R
import com.example.mvvmrecycler.domain.model.CharacterCard
import javax.inject.Inject

class StarWarsAdapter (private val listCharacter: List<CharacterCard>): RecyclerView.Adapter<StarWarsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarWarsViewHolder {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)

        return StarWarsViewHolder(layoutInflater.inflate(R.layout.item_recycler, parent,false))

    }

    override fun onBindViewHolder(holder: StarWarsViewHolder, position: Int) {

        holder.render(listCharacter[position])

    }

    override fun getItemCount(): Int {

        return listCharacter.count()

    }


}