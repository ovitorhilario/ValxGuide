package com.example.valxguide.ui.main.weapons.landing.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.valxguide.R
import com.example.valxguide.data.model.Weapon
import com.example.valxguide.databinding.FragmentWeaponsBinding
import com.example.valxguide.ui.main.weapons.landing.adapter.WeaponsAdapter
import com.example.valxguide.ui.main.activity.viewmodel.MainViewModel
import com.example.valxguide.ui.main.weapons.detail.view.WeaponDetailFragment
import com.example.valxguide.utils.extensions.openFragment
import com.example.valxguide.utils.extensions.popBackStack
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class WeaponsFragment : Fragment() {

    private var _binding : FragmentWeaponsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, saved: Bundle?): View {
        _binding = FragmentWeaponsBinding.inflate(inflater, group, false)
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
        viewModel.weapons.observe(viewLifecycleOwner) { weapons ->
            weapons?.let { setupAdapter(it.data) }
        }
    }

    private fun setupAdapter(weapons: List<Weapon>) {
        binding.rvWeapons.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.rvWeapons.adapter = WeaponsAdapter(
            repository = weapons,
            openWeaponDetail = { weapon ->
                openFragment<WeaponDetailFragment>(
                    R.id.fcv_main_app,
                    bundleOf("WEAPON_ID" to weapon.uuid))
            }
        )
    }

    private fun setupClickListeners() {
        binding.btnWeaponsReturn.setOnClickListener { popBackStack() }
    }
}