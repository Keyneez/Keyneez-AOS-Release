package com.release.keyneez.presentation.main.explore.recent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentExploreRecentBinding
import com.release.keyneez.util.binding.BindingFragment

class RecentFragment :
    BindingFragment<FragmentExploreRecentBinding>(R.layout.fragment_explore_recent) {

    private val viewModel: RecentViewModel by viewModels()
    private lateinit var recentAdapter: RecentAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecentAdapter()
    }

    private fun initRecentAdapter() {
        recentAdapter = RecentAdapter()
        binding.rvExploreRecent.adapter = recentAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecentFragment()
    }
}
