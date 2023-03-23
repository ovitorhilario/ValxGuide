package com.example.valxguide.ui.main.agents.detail.adapter

import android.content.res.Resources
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.valxguide.R
import com.example.valxguide.data.model.Ability
import com.example.valxguide.databinding.AgentDetailAbilitiesItemBinding
import com.example.valxguide.databinding.AgentDetailDescItemBinding
import com.example.valxguide.databinding.AgentDetailIconItemBinding
import com.example.valxguide.databinding.BackItemBinding
import com.squareup.picasso.Picasso

class AgentDetailAdapter(
    private val repository: List<Pair<Int, Any>>,
    private val resources: Resources,
    private val actionBack: () -> Unit,

    ) : Adapter<AgentDetailAdapter.Holder>() {
    abstract class Holder(item: View) : ViewHolder(item) {
        abstract fun bind(data: Any)
    }

    inner class TopBarHolder(binding: BackItemBinding) : Holder(binding.root) {
        private val tvAgentName = binding.tvBackTittle
        private val btnBack = binding.ivBackIcon

        override fun bind(data: Any) {
            val name = data as? String
            name.let { tvAgentName.text = it }
            btnBack.setOnClickListener { actionBack() }
        }
    }

    inner class AgentIconHolder(binding: AgentDetailIconItemBinding) : Holder(binding.root) {
        private val ivAgentIcon = binding.ivAgentDetailIcon

        override fun bind(data: Any) {
            val agentUrl = data as? String
            agentUrl?.let {
                Picasso.get().load(it).into(ivAgentIcon)
            }
        }
    }

    inner class AgentDescHolder(binding: AgentDetailDescItemBinding) : Holder(binding.root) {

        private val tvAgentType = binding.tvAgentDetailTypeContent
        private val ivAgentTypeIcon = binding.ivAgentDetailTypeIcon
        private val tvAgentDesc = binding.tvAgentDetailDesc

        override fun bind(data: Any) {
            val (type, typeIconUrl, description) = data as Triple<String, String, String>

            tvAgentType.text = type
            tvAgentDesc.text = getFullText(resources.getString(R.string.agent_description), description)

            Picasso.get().load(typeIconUrl).into(ivAgentTypeIcon)
        }
    }

    inner class AgentAbilitiesHolder(binding: AgentDetailAbilitiesItemBinding) : Holder(binding.root) {

        private val tvAbilityName = binding.tvAgentDetailAbilityName
        private val tvAbilityDesc = binding.tvAgentDetailAbilityDesc
        private val ivAbilityIcon = binding.ivAgentDetailAbilityIcon

        override fun bind(data: Any) {
            val ability = data as? Ability

            ability?.let {
                tvAbilityName.text = it.displayName
                tvAbilityDesc.text = getFullText(resources.getString(R.string.agent_description), it.description)

                Picasso.get().load(it.displayIcon).into(ivAbilityIcon)
            }
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): Holder {

        val inflater = LayoutInflater.from(group.context)

        return when (viewType) {
            0 -> TopBarHolder(
                BackItemBinding.inflate(inflater, group, false)
            )
            1 -> AgentIconHolder(
                AgentDetailIconItemBinding.inflate(inflater, group, false)
            )
            2 -> AgentDescHolder(
                AgentDetailDescItemBinding.inflate(inflater, group, false)
            )
            3 -> AgentAbilitiesHolder(
                AgentDetailAbilitiesItemBinding.inflate(inflater, group, false)
            )
            else -> throw IllegalArgumentException("unknown holder type")
        }

        /*
        0 -> Nome do Agente,
        1 -> Imagem do Agente,
        2 -> Pais de Origen, Classe, Descrição
        3 -> Abilitys
        */
    }

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(repository[position].second)

    override fun getItemViewType(position: Int): Int = repository[position].first

    override fun getItemCount(): Int = repository.size

    private fun getFullText(start: String, end: String) : Spannable {
        val spannable = SpannableStringBuilder()

        val whiteColor = ForegroundColorSpan(Color.WHITE)
        val redColor = ForegroundColorSpan(resources.getColor(R.color.red))

        return spannable.apply {
            append(start, redColor, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            append(" ")
            append(end, whiteColor, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}