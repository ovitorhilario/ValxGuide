package com.example.valxguide.utils.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace

fun Fragment.popBackStack() {
    this.requireActivity().supportFragmentManager.popBackStack()
}

inline fun <reified T : Fragment> Fragment.openFragment(fragmentContainerId: Int, bundle: Bundle?, addToStack: Boolean = true) {
    requireActivity().supportFragmentManager.commit {
        replace<T>(fragmentContainerId, null, bundle)
        setReorderingAllowed(true)
        if(addToStack) this.addToBackStack(null)
    }
}