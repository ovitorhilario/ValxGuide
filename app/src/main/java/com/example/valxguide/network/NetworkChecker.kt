package com.example.valxguide.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

typealias voidAction = () -> Unit

class NetworkChecker(private val connectivityManager: ConnectivityManager?) {

    fun performAction(onSuccess : voidAction, onFailure: voidAction) {
        when(hasInternet()) {
            true -> onSuccess()
            false -> onFailure()
        }
    }

    private fun hasInternet(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager?.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

            return capabilities.run {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                hasTransport(NetworkCapabilities.TRANSPORT_VPN)
            }

        } else {
            val activeNetworkInfo = connectivityManager?.activeNetworkInfo
            return activeNetworkInfo?.run {
                type == ConnectivityManager.TYPE_WIFI || type == ConnectivityManager.TYPE_MOBILE
            } ?: false
        }
    }
}