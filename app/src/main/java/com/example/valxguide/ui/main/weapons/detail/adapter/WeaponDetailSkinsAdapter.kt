package com.example.valxguide.ui.main.weapons.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.valxguide.databinding.WeaponDetailSkinItemBinding
import com.squareup.picasso.Picasso

class WeaponDetailSkinsAdapter(
    private val repository: List<String>
) : Adapter<WeaponDetailSkinsAdapter.WeaponSkinHolder>() {

    inner class WeaponSkinHolder(binding: WeaponDetailSkinItemBinding) : ViewHolder(binding.root) {
        private val ivWeaponSkin = binding.ivWeaponDetailSkin

        fun bind(iconUrl: String) {
            Picasso.get().load(iconUrl).into(ivWeaponSkin)
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): WeaponSkinHolder {
        return WeaponSkinHolder(
            WeaponDetailSkinItemBinding.inflate(LayoutInflater.from(group.context), group ,false)
        )
    }

    override fun getItemCount(): Int = repository.size

    override fun onBindViewHolder(holder: WeaponSkinHolder, position: Int) = holder.bind(repository[position])
}