package com.example.valxguide.ui.main.cards.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.valxguide.databinding.PlayerCardItemBinding
import com.squareup.picasso.Picasso

class PlayerCardsAdapter(
    private val repository: List<String>
) : Adapter<PlayerCardsAdapter.PlayCardHolder>() {

    inner class PlayCardHolder(binding: PlayerCardItemBinding) : ViewHolder(binding.root) {
        private val ivPlayerCardIcon = binding.ivPlayerCardIcon

        fun bind(iconUrl: String) {
            ivPlayerCardIcon.clipToOutline = true
            Picasso.get().load(iconUrl).into(ivPlayerCardIcon)
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): PlayCardHolder {
        return PlayCardHolder(
            PlayerCardItemBinding.inflate(LayoutInflater.from(group.context), group, false)
        )
    }

    override fun getItemCount(): Int = repository.size

    override fun onBindViewHolder(holder: PlayCardHolder, position: Int) {
        holder.bind(repository[position])
    }
}