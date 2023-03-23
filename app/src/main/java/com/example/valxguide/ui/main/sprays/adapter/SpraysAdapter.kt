package com.example.valxguide.ui.main.sprays.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.valxguide.databinding.SprayItemBinding
import com.squareup.picasso.Picasso

class SpraysAdapter(
    private val repository: List<Pair<String, String>>,
) : Adapter<SpraysAdapter.SprayHolder>() {

    inner class SprayHolder(binding: SprayItemBinding) : ViewHolder(binding.root) {
        private val tvSprayTittle = binding.tvSprayTittle
        private val ivSprayIcon = binding.ivSprayIcon

        fun bind(sprayInfo: Pair<String, String>) {
            tvSprayTittle.text = sprayInfo.first
            ivSprayIcon.clipToOutline = true
            Picasso.get().load(sprayInfo.second).into(ivSprayIcon)
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): SprayHolder {
        return SprayHolder(
            SprayItemBinding.inflate(LayoutInflater.from(group.context), group, false)
        )
    }

    override fun getItemCount(): Int = repository.size

    override fun onBindViewHolder(holder: SprayHolder, position: Int) {
        holder.bind(repository[position])
    }
}