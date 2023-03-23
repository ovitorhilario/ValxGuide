package com.example.valxguide.ui.main.agents.landing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.valxguide.data.model.Agent
import com.example.valxguide.databinding.AgentItemBinding
import com.squareup.picasso.Picasso

class AgentsAdapter(
    private val repository: List<Agent>,
    private val openCurrentAgent: (Agent) -> Unit
) : Adapter<AgentsAdapter.AgentHolder>() {

    inner class AgentHolder(binding: AgentItemBinding) : ViewHolder(binding.root) {
        private val tvAgentName = binding.tvAgentItemName
        private val ivAgentIcon = binding.ivAgentItemIcon
        private val container = binding.root

        fun bind(agent: Agent) {

            tvAgentName.text = agent.displayName

            Picasso.get()
                .load(agent.fullPortrait)
                .into(ivAgentIcon)

            container.setOnClickListener { openCurrentAgent(agent) }
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): AgentHolder {
        return AgentHolder(
            AgentItemBinding.inflate(LayoutInflater.from(group.context), group, false)
        )
    }

    override fun getItemCount(): Int = repository.size

    override fun onBindViewHolder(holder: AgentHolder, position: Int) = holder.bind(repository[position])
}