package com.example.valxguide.ui.main.agents.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valxguide.data.model.Agent
import com.example.valxguide.databinding.FragmentAgentDetailBinding
import com.example.valxguide.ui.main.activity.viewmodel.MainViewModel
import com.example.valxguide.ui.main.agents.detail.adapter.AgentDetailAdapter
import com.example.valxguide.utils.extensions.popBackStack
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AgentDetailFragment : Fragment() {

    private var _binding: FragmentAgentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, saved: Bundle?): View {
        _binding = FragmentAgentDetailBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val agentUI = requireArguments().getString("AGENT_ID")
        agentUI?.let { setupUI(it) }
    }

    private fun setupUI(agentId: String) {
        setupObservers(agentId)
    }

    private fun setupObservers(agentId: String) {
        viewModel.agents.observe(viewLifecycleOwner) { agents ->
            agents?.data?.let { agentList ->
                agentList.firstOrNull { it.uuid == agentId }
                    ?.let { agent -> setupAdapter(agent) }
            }
        }
    }

    private fun setupAdapter(agent: Agent) {

        val repository = mutableListOf<Pair<Int, Any>>(
            0 to agent.displayName,
            1 to agent.fullPortrait,
            2 to Triple(agent.role.displayName, agent.role.displayIcon, agent.description),
        )

        agent.abilities.forEach { ability -> repository.add(3 to ability) }

        val adapter = AgentDetailAdapter(
            repository = repository,
            resources = resources,
            actionBack = { popBackStack() }
        )

        binding.rvAgentDetail.adapter = adapter
    }
}