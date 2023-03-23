package com.example.valxguide.ui.main.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.valxguide.R
import com.example.valxguide.databinding.FragmentHomeBinding
import com.example.valxguide.ui.main.home.adapter.GuideHomeAdapter
import com.example.valxguide.ui.main.agents.landing.view.AgentsFragment
import com.example.valxguide.ui.main.buddies.view.GunBuddiesFragment
import com.example.valxguide.ui.main.cards.view.PlayerCardsFragment
import com.example.valxguide.ui.main.maps.view.MapsFragment
import com.example.valxguide.ui.main.ranks.view.RanksFragment
import com.example.valxguide.ui.main.sprays.view.SpraysFragment
import com.example.valxguide.ui.main.weapons.landing.view.WeaponsFragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, saved: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        val adapter = GuideHomeAdapter (
            repository = GuideList,
            openCurrentGuide = { currentGuide ->
                when(currentGuide) {
                    Guide.AGENTS -> openFragment<AgentsFragment>()
                    Guide.WEAPONS -> openFragment<WeaponsFragment>()
                    Guide.RANKS -> openFragment<RanksFragment>()
                    Guide.SPRAYS -> openFragment<SpraysFragment>()
                    Guide.CARDS -> openFragment<PlayerCardsFragment>()
                    Guide.MAPS -> openFragment<MapsFragment>()
                    Guide.GUN_BUDDIES -> openFragment<GunBuddiesFragment>()
                }
            }
        )

        binding.rvHomeGuideItems.adapter = adapter
    }

    private inline fun <reified F : Fragment> openFragment() {
        requireActivity().supportFragmentManager.commit {
            replace<F>(R.id.fcv_main_app)
            addToBackStack(null)
            setReorderingAllowed(true)
        }
    }

    companion object {

        enum class Guide(val tittle: String, val path: String) {
            AGENTS("Agents", "agents.png"),
            WEAPONS("Weapons", "weapons.png"),
            RANKS("Ranks", "ranks.png"),
            SPRAYS("Sprays", "sprays.png"),
            CARDS("Player\nCards", "cards.png"),
            MAPS("Maps", "maps.png"),
            GUN_BUDDIES("Gun\nBuddies", "gun_buddies.png")
        }

        val GuideList = listOf (
            "Valorant Guide",
            Guide.AGENTS,
            Guide.WEAPONS,
            Guide.RANKS,
            Guide.SPRAYS,
            Guide.CARDS,
            Guide.MAPS,
            Guide.GUN_BUDDIES
        )
    }
}