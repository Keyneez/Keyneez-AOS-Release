package com.release.keyneez.presentation.main.explore.recent

import android.os.Bundle
import android.view.View
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentExploreRecentBinding
import com.release.keyneez.util.binding.BindingFragment

class RecentFragment :
    BindingFragment<FragmentExploreRecentBinding>(R.layout.fragment_explore_recent) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): RecentFragment {
            return RecentFragment()
        }
    }
}
