package com.release.keyneez.presentation.onboarding

import android.os.Bundle
import android.view.View
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentOnSecondBinding
import com.release.keyneez.util.binding.BindingFragment

class OnSecondFragment : BindingFragment<FragmentOnSecondBinding>(R.layout.fragment_on_second) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() = OnSecondFragment()
    }
}
