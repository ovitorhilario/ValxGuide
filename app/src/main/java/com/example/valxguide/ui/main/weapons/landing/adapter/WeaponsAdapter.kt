package com.example.valxguide.ui.main.weapons.landing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.valxguide.data.model.Weapon
import com.example.valxguide.databinding.WeaponItemBinding
import com.squareup.picasso.Picasso

class WeaponsAdapter(
    private val repository : List<Weapon>,
    private val openWeaponDetail: (Weapon) -> Unit,

) : Adapter<WeaponsAdapter.WeaponHolder>() {

    inner class WeaponHolder(binding: WeaponItemBinding) : ViewHolder(binding.root) {
        private val tvWeaponName = binding.tvWeaponName
        private val ivWeaponIcon = binding.ivWeaponIcon
        private val container = binding.root

        fun bind(weapon: Weapon) {
            tvWeaponName.text = weapon.displayName
            Picasso.get().load(weapon.displayIcon).into(ivWeaponIcon)

            container.setOnClickListener { openWeaponDetail(weapon) }
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): WeaponHolder {
        return WeaponHolder(
            WeaponItemBinding.inflate(LayoutInflater.from(group.context), group, false))
    }

    override fun getItemCount(): Int = repository.size

    override fun onBindViewHolder(holder: WeaponHolder, position: Int) = holder.bind(repository[position])
}