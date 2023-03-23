package com.example.valxguide.ui.main.weapons.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valxguide.data.model.Weapon
import com.example.valxguide.databinding.FragmentWeaponDetailBinding
import com.example.valxguide.ui.main.activity.viewmodel.MainViewModel
import com.example.valxguide.ui.main.weapons.detail.adapter.WeaponDetailAdapter
import com.example.valxguide.utils.extensions.popBackStack
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class WeaponDetailFragment : Fragment() {

    private var _binding : FragmentWeaponDetailBinding? = null
    private val binding get () = _binding!!
    private val viewModel: MainViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, saved: Bundle?): View {
        _binding = FragmentWeaponDetailBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateArgs()
    }

    private fun validateArgs() {
        try {
            val weaponId = requireArguments().getString("WEAPON_ID")
            setupUI(weaponId)
        } catch (e: Exception) {
            popBackStack()
        }
    }

    private fun setupUI(weaponId: String?) {
        weaponId?.let { setupObservers(it) }
    }

    private fun setupObservers(weaponId: String) {
        viewModel.weapons.observe(viewLifecycleOwner) { weapons ->
            weapons?.data?.firstOrNull { it.uuid == weaponId }
                ?.let { weapon -> setupAdapter(weapon) }
        }
    }

    private fun setupAdapter(weapon: Weapon) {

        val multipleList = mutableListOf<Pair<Int, Any>>()

        multipleList.add(TOP_BAR_TYPE to weapon.displayName)
        multipleList.add(ICON_WEAPON_TYPE to weapon.displayIcon)

        if (weapon.weaponStats != null && weapon.shopData != null) {
            multipleList.add(DESCRIPTION_TYPE to weapon)
            multipleList.add(DAMAGE_TYPE to weapon.weaponStats.damageRanges)
        }

        multipleList.add(SKINS_TYPE to weapon.skins.filter { it.contentTierUuid != null }.mapNotNull { it.displayIcon } )

        binding.rvWeaponDetail.adapter = WeaponDetailAdapter(
            repository = multipleList,
            onClickBack = { popBackStack() }
        )
    }

    companion object {
        private const val TOP_BAR_TYPE = 0
        private const val ICON_WEAPON_TYPE = 1
        private const val DESCRIPTION_TYPE = 2
        private const val DAMAGE_TYPE = 3
        private const val SKINS_TYPE = 4
    }
}