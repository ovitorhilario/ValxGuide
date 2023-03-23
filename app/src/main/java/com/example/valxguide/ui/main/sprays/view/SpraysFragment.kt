package com.example.valxguide.ui.main.sprays.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.valxguide.data.model.Spray
import com.example.valxguide.databinding.FragmentSpraysBinding
import com.example.valxguide.ui.main.activity.viewmodel.MainViewModel
import com.example.valxguide.ui.main.sprays.adapter.SpraysAdapter
import com.example.valxguide.utils.extensions.popBackStack
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SpraysFragment : Fragment() {

    private var _binding : FragmentSpraysBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSpraysBinding.inflate(LayoutInflater.from(group?.context), group, false)
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
        viewModel.sprays.observe(viewLifecycleOwner) { sprays ->
            sprays?.data?.let { sprayList ->
                setupAdapter(sprayList)
            }
        }
    }

    private fun setupAdapter(sprays: List<Spray>) {
        binding.rvSprays.layoutManager = GridLayoutManager(
            context, 2, GridLayoutManager.VERTICAL, false)

        binding.rvSprays.adapter = SpraysAdapter(sprays.map { it.toUI() })
    }

    private fun Spray.toUI() : Pair<String, String> {
        return this.displayName to this.displayIcon
    }

    private fun setupClickListeners() {
        binding.btnSpraysReturn.setOnClickListener { popBackStack() }
    }
}