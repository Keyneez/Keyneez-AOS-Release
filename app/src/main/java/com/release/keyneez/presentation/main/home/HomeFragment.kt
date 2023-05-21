package com.release.keyneez.presentation.main.home

import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentHomeBinding
import com.release.keyneez.util.binding.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
