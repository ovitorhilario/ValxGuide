package com.example.valxguide.ui.main.maps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.valxguide.databinding.MapItemBinding
import com.squareup.picasso.Picasso

class MapsAdapter(
    private val repository: List<Triple<String, String, String>>,

) : RecyclerView.Adapter<MapsAdapter.MapHolder>() {

    inner class MapHolder(binding: MapItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val tvMapItemName = binding.tvMapItemName
        private val ivMapItemBackgroundIcon = binding.ivMapItemBackgroundIcon
        private val ivMapItemRadarIcon = binding.ivMapItemRadarIcon

        fun bind(mapInfo: Triple<String, String, String>) {
            tvMapItemName.text = mapInfo.first
            Picasso.get().load(mapInfo.second).into(ivMapItemRadarIcon)
            Picasso.get().load(mapInfo.third).into(ivMapItemBackgroundIcon)

            ivMapItemRadarIcon.bringToFront()
            tvMapItemName.bringToFront()
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): MapHolder {
        return MapHolder(
            MapItemBinding.inflate(LayoutInflater.from(group.context), group, false)
        )
    }

    override fun getItemCount(): Int = repository.size

    override fun onBindViewHolder(holder: MapHolder, position: Int) {
        holder.bind(repository[position])
    }
}