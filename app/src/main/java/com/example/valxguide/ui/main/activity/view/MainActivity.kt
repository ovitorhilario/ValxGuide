package com.example.valxguide.ui.main.activity.view

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.valxguide.R
import com.example.valxguide.databinding.ActivityMainBinding
import com.example.valxguide.network.NetworkChecker
import com.example.valxguide.ui.main.home.view.HomeFragment
import com.example.valxguide.ui.main.activity.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = getColor(R.color.bg)
        checkConnection()
    }

    private fun checkConnection() {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

        NetworkChecker(manager).performAction(
            onSuccess = {
                mViewModel.fetchData()
                setupObservers()
            },
            onFailure = {
               showInternetError()
            }
        )
    }

    private fun setupUI() {
        window.statusBarColor = resources.getColor(R.color.bg)

        supportFragmentManager.commit {
            replace<HomeFragment>(binding.fcvMainApp.id)
            setReorderingAllowed(true)
        }
    }

    private fun setupObservers() {

        mViewModel.currentDataState.observe(this) { state ->
            if(state == MainViewModel.DataState.Full) {
                setupUI()
            } else if (state == MainViewModel.DataState.Error) {
                showInternetError()
            }
        }

        mViewModel.anyIsLoading.observe(this) { isLoading ->
            if(isLoading) {
                binding.pbMainApp.show()
            } else {
                binding.pbMainApp.hide()
            }
        }
    }

    private fun showInternetError() {
        supportFragmentManager.commit {
            replace<InternetErrorFragment>(binding.fcvMainApp.id)
            setReorderingAllowed(true)
        }
    }
}