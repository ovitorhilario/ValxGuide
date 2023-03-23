package com.example.valxguide.ui.main.ranks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.valxguide.databinding.RankItemBinding
import com.squareup.picasso.Picasso

class RankAdapter(
    private val repository: List<Pair<String, String>>
) : Adapter<RankAdapter.RankHolder>() {

    inner class RankHolder(binding: RankItemBinding) : ViewHolder(binding.root) {
        private val tvRankItemTierName = binding.tvRankItemTierName
        private val ivRankItemIcon = binding.ivRankItemIcon

        fun bind(data: Pair<String, String>) {
            tvRankItemTierName.text = data.first
            Picasso.get().load(data.second).into(ivRankItemIcon)
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): RankHolder {
        return RankHolder(
            RankItemBinding.inflate(LayoutInflater.from(group.context), group, false)
        )
    }

    override fun getItemCount(): Int = repository.size

    override fun onBindViewHolder(holder: RankHolder, position: Int) {
        holder.bind(repository[position])
    }

}
