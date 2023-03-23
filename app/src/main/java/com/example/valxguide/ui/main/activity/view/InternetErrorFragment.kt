package com.example.valxguide.ui.main.activity.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valxguide.databinding.FragmentInternetErrorBinding

class InternetErrorFragment : Fragment() {

    private var _binding : FragmentInternetErrorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, saved: Bundle?): View? {
        _binding = FragmentInternetErrorBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnInternetErrorTryAgain.setOnClickListener {
            (requireActivity() as MainActivity).finish()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }
}