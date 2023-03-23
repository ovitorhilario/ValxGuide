package com.example.valxguide.ui.main.maps.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valxguide.data.model.MapInfo
import com.example.valxguide.databinding.FragmentMapsBinding
import com.example.valxguide.ui.main.activity.viewmodel.MainViewModel
import com.example.valxguide.ui.main.maps.adapter.MapsAdapter
import com.example.valxguide.utils.extensions.popBackStack
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MapsFragment : Fragment() {

    private var _binding : FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMapsBinding.inflate(LayoutInflater.from(group?.context), group, false)
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
        viewModel.maps.observe(viewLifecycleOwner) { maps ->
            maps?.data?.let { mapsList ->
                setupAdapter(mapsList)
            }
        }
    }

    private fun MapInfo.toUI() : Triple<String, String, String> {
        return Triple(this.displayName, this.displayIcon, this.splash)
    }

    private fun setupAdapter(maps: List<MapInfo>) {
        binding.rvMaps.adapter = MapsAdapter(maps.map { it.toUI() })
    }

    private fun setupClickListeners() {
        binding.btnMapsReturn.setOnClickListener { popBackStack() }
    }
}