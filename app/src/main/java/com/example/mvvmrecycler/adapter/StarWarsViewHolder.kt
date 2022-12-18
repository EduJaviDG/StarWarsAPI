package com.example.mvvmrecycler.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmrecycler.databinding.ItemRecyclerBinding
import com.example.mvvmrecycler.domain.model.CharacterCard

class StarWarsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    private val binding = ItemRecyclerBinding.bind(itemView)

    fun render(item: CharacterCard){

        binding.tvTitle.text = item.name

        binding.tvHeightData.text = item.height

        binding.tvMassData.text = item.mass

        binding.tvHairData.text = item.hair

        binding.tvSkinData.text = item.skin

        binding.tvEyeData.text = item.eye

        binding.tvBirthdayData.text = item.birth

        binding.tvGenderData.text = item.gender

        binding.tvPlanetData.text = item.homeworld

        binding.tvSpecieData.text = item.specie

        binding.tvLanguageData.text = item.language

        binding.tvVehicleData.text = item.vehicles.first()

        binding.tvStarShip.text = item.starShips.first()

    }

}