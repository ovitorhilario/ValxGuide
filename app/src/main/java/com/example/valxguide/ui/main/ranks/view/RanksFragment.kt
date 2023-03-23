package com.example.valxguide.ui.main.ranks.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valxguide.data.model.Rank
import com.example.valxguide.databinding.FragmentRanksBinding
import com.example.valxguide.ui.main.activity.viewmodel.MainViewModel
import com.example.valxguide.ui.main.ranks.adapter.RanksAdapter
import com.example.valxguide.utils.extensions.popBackStack
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class RanksFragment : Fragment() {

    private var _binding : FragmentRanksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, saved: Bundle?): View {
        _binding = FragmentRanksBinding.inflate(inflater, group, false)
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

    private fun setupObservers() {
        viewModel.ranks.observe(viewLifecycleOwner) { ranks ->
            ranks?.let { setupAdapter(it) }
        }
    }

    private fun setupAdapter(ranks: Rank) {
        val rawRanks = ranks.copy()

        val listToAdapter = rawRanks.tiers
            .groupBy { it.divisionName }
            .mapValues { (_, value) -> value.map { it.tierName to it.largeIcon } }
            .toList()

        binding.rvRanks.adapter = RanksAdapter(listToAdapter)
    }

    private fun setupClickListeners() {
        binding.btnRanksReturn.setOnClickListener { popBackStack() }
    }
}