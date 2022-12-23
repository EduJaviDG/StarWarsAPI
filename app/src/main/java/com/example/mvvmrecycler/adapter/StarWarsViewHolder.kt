package com.example.mvvmrecycler.adapter

import android.content.DialogInterface
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmrecycler.databinding.ItemRecyclerBinding
import com.example.mvvmrecycler.domain.model.CharacterCard

class StarWarsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    private val binding = ItemRecyclerBinding.bind(itemView)

    fun render(item: CharacterCard, onClickListener:(character: CharacterCard, position: Int)->Unit,
               position: Int){

        binding.tvTitle.text = item.name

        binding.cvInfo.isVisible = item.isExnpaded

        binding.tvHeightData.text = item.height

        binding.tvMassData.text = item.mass

        binding.tvHairData.text = item.hair.replaceFirstChar { it.titlecase() }

        binding.tvSkinData.text = item.skin.replaceFirstChar { it.titlecase() }

        binding.tvEyeData.text = item.eye.replaceFirstChar { it.titlecase() }

        binding.tvBirthdayData.text = item.birth

        binding.tvGenderData.text = item.gender.replaceFirstChar { it.titlecase() }

        binding.tvPlanetData.text = item.homeworld.replaceFirstChar { it.titlecase() }

        itemView.setOnClickListener{

            onClickListener(item,position)

        }

    }

}