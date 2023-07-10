package com.release.keyneez.presentation.onboarding

import android.os.Bundle
import android.view.View
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentOnFirstBinding
import com.release.keyneez.util.binding.BindingFragment

class OnFirstFragment : BindingFragment<FragmentOnFirstBinding>(R.layout.fragment_on_first) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() = OnFirstFragment()
    }
}
