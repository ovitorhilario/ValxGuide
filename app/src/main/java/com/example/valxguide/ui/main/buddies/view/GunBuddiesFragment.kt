package com.example.valxguide.ui.main.buddies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.valxguide.data.model.Buddie
import com.example.valxguide.databinding.FramentGunBuddiesBinding
import com.example.valxguide.ui.main.activity.viewmodel.MainViewModel
import com.example.valxguide.ui.main.buddies.adapter.GunBuddiesAdapter
import com.example.valxguide.utils.extensions.popBackStack
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class GunBuddiesFragment : Fragment() {

    private var _binding : FramentGunBuddiesBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FramentGunBuddiesBinding.inflate(LayoutInflater.from(group?.context), group, false)
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
        viewModel.gunBuddies.observe(viewLifecycleOwner) { gunBuddies ->
            gunBuddies?.data?.let { buddies ->
                setupAdapter(buddies)
            }
        }
    }

    private fun setupAdapter(buddies: List<Buddie>) {
        binding.rvGunBuddies.layoutManager = GridLayoutManager(
            context, 2, GridLayoutManager.VERTICAL, false)

        binding.rvGunBuddies.adapter = GunBuddiesAdapter(buddies.map { it.toUI() })
    }

    private fun Buddie.toUI() : Pair<String, String> {
        return this.displayName to this.displayIcon
    }

    private fun setupClickListeners() {
        binding.btnGunBuddiesReturn.setOnClickListener { popBackStack() }
    }
}