package com.example.valxguide.ui.main.agents.landing.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.valxguide.R
import com.example.valxguide.data.model.Agent
import com.example.valxguide.databinding.FragmentAgentsBinding
import com.example.valxguide.ui.main.agents.landing.adapter.AgentsAdapter
import com.example.valxguide.ui.main.activity.viewmodel.MainViewModel
import com.example.valxguide.ui.main.agents.detail.view.AgentDetailFragment
import com.example.valxguide.utils.extensions.openFragment
import com.example.valxguide.utils.extensions.popBackStack
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AgentsFragment : Fragment() {

    private var _binding : FragmentAgentsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, saved: Bundle?): View {
        _binding = FragmentAgentsBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupClickListeners()
    }

    private fun setupUI() {
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.agents.observe(viewLifecycleOwner) { agents ->
            agents?.let { setupAdapter(it.data) }
        }
    }

    private fun setupAdapter(agents: List<Agent>) {

        binding.rvAgents.layoutManager = GridLayoutManager (
            context, 2, GridLayoutManager.VERTICAL, false)

        binding.rvAgents.adapter = AgentsAdapter(
            repository = agents,
            openCurrentAgent = openAgent
        )
    }

    private val openAgent = { agent : Agent ->
        openFragment<AgentDetailFragment>(R.id.fcv_main_app, bundleOf("AGENT_ID" to agent.uuid))
    }

    private fun setupClickListeners() {
        binding.btnAgentsReturn.setOnClickListener { popBackStack() }
    }
}