package com.release.keyneez.presentation.main.like

import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentLikeBinding
import com.release.keyneez.util.binding.BindingFragment

class LikeFragment : BindingFragment<FragmentLikeBinding>(R.layout.fragment_like) {

    companion object {
        fun newInstance() = LikeFragment()
    }
}
