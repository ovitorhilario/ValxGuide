package com.example.valxguide.ui.main.cards.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.valxguide.databinding.FragmentPlayerCardsBinding
import com.example.valxguide.ui.main.activity.viewmodel.MainViewModel
import com.example.valxguide.ui.main.cards.adapter.PlayerCardsAdapter
import com.example.valxguide.utils.extensions.popBackStack
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class PlayerCardsFragment : Fragment() {

    private var _binding : FragmentPlayerCardsBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, saved: Bundle?): View {
        _binding = FragmentPlayerCardsBinding.inflate(LayoutInflater.from(group?.context), group, false)
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

    private fun setupClickListeners() {
        binding.btnPlayerCardsReturn.setOnClickListener { popBackStack() }
    }

    private fun setupObservers() {
        viewModel.cards.observe(viewLifecycleOwner) { playerCards ->
            playerCards?.data?.let { cards ->
                setupAdapter(cards.map { it.largeArt })
            }
        }
    }

    private fun setupAdapter(cards: List<String>) {
        binding.rvPlayerCards.layoutManager = GridLayoutManager(
            context, 2, GridLayoutManager.VERTICAL, false)

        binding.rvPlayerCards.adapter = PlayerCardsAdapter(cards)
    }
}