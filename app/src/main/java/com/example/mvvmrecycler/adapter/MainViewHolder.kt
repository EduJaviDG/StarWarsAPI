package com.example.mvvmrecycler.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmrecycler.databinding.ItemRecyclerBinding
import com.example.mvvmrecycler.ui.model.User

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemRecyclerBinding.bind(view)

    fun render(itemUser: User){

        binding.tvTitle.text = itemUser.name

        binding.tvDescription.text = itemUser.description

        Glide.with(binding.ivCircleImage.context).load(itemUser.image).into(binding.ivCircleImage)

    }

}