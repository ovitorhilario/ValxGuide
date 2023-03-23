package com.example.valxguide.ui.main.buddies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.valxguide.databinding.GunBuddiesItemBinding
import com.squareup.picasso.Picasso

class GunBuddiesAdapter(
    private val repository: List<Pair<String, String>>
) : RecyclerView.Adapter<GunBuddiesAdapter.GunBuddieHolder>() {

    inner class GunBuddieHolder(binding: GunBuddiesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val tvGunBuddiesTittle = binding.tvGunBuddiesTittle
        private val ivGunBuddiesIcon = binding.ivGunBuddiesIcon

        fun bind(buddieInfo: Pair<String, String>) {
            tvGunBuddiesTittle.text = buddieInfo.first
            ivGunBuddiesIcon.clipToOutline = true
            Picasso.get().load(buddieInfo.second).into(ivGunBuddiesIcon)
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): GunBuddieHolder {
        return GunBuddieHolder(
            GunBuddiesItemBinding.inflate(LayoutInflater.from(group.context), group, false)
        )
    }

    override fun getItemCount(): Int = repository.size

    override fun onBindViewHolder(holder: GunBuddieHolder, position: Int) {
        holder.bind(repository[position])
    }
}