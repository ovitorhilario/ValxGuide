package com.example.valxguide.ui.main.ranks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.valxguide.databinding.RanksItemBinding

class RanksAdapter(
    private val repository: List<Pair<String, List<Pair<String, String>>>>,
) : Adapter<RanksAdapter.RanksHolder>() {

    inner class RanksHolder(binding: RanksItemBinding) : ViewHolder(binding.root) {
        private val tvRanksItemTittle = binding.tvRanksItemTittle
        private val rvRanksItem = binding.rvRanksItem

        fun bind(data: Pair<String, List<Pair<String, String>>>) {
            tvRanksItemTittle.text = data.first
            rvRanksItem.adapter = RankAdapter(data.second)
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): RanksHolder {
        return RanksHolder(
            RanksItemBinding.inflate(LayoutInflater.from(group.context), group, false)
        )
    }

    override fun getItemCount(): Int = repository.size

    override fun onBindViewHolder(holder: RanksHolder, position: Int) {
        holder.bind(repository[position])
    }
}