package com.example.valxguide.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.valxguide.databinding.GuideItemHomeBinding
import com.example.valxguide.databinding.GuideTextHomeBinding
import com.example.valxguide.ui.main.home.view.HomeFragment
import com.squareup.picasso.Picasso

class GuideHomeAdapter
(
    private val repository: List<Any>,
    private val openCurrentGuide: (HomeFragment.Companion.Guide) -> Unit,

    ) : RecyclerView.Adapter<GuideHomeAdapter.GuideHolder>() {

    abstract class GuideHolder(item: View) : ViewHolder(item) {
        abstract fun bind(data: Any)
    }

    inner class GuideTextHolder(binding: GuideTextHomeBinding) : GuideHolder(binding.root) {
        private val tvTopBar = binding.tvTopBarHome

        override fun bind(data: Any) {
            val realData = data as? String

            realData?.let {
                tvTopBar.text = it
            }
        }
    }

    inner class GuideItemHolder(binding: GuideItemHomeBinding) : GuideHolder(binding.root) {
        private val tvGuideText = binding.tvGuideItem
        private val ivGuideIcon = binding.ivGuideItem
        private val container = binding.root

        override fun bind(data: Any) {
            val realData = data as? HomeFragment.Companion.Guide

            realData?.let { guide ->
                tvGuideText.text = guide.tittle
                Picasso.get().load("file:///android_asset/${guide.path}").into(ivGuideIcon)
                container.setOnClickListener { openCurrentGuide(realData) }
            }
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): GuideHolder {
        return when(viewType) {
            0 -> GuideTextHolder(GuideTextHomeBinding.inflate(LayoutInflater.from(group.context), group, false))
            1 -> GuideItemHolder(GuideItemHomeBinding.inflate(LayoutInflater.from(group.context), group, false))
            else -> throw IllegalArgumentException("illegal repository type for holder")
        }

        // 0 -> GuideTextHolder
        // 1 -> GuideItemHolder
    }

    override fun getItemViewType(position: Int): Int {
        return when(repository[position]) {
            is String -> 0
            is HomeFragment.Companion.Guide -> 1
            else -> throw IllegalArgumentException("illegal repository type for holder")
        }
    }

    override fun getItemCount(): Int = repository.size

    override fun onBindViewHolder(holder: GuideHolder, position: Int) = holder.bind(repository[position])
}