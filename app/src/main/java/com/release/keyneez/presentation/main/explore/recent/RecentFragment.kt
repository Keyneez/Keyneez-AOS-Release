package com.release.keyneez.presentation.main.explore.recent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.data.entity.ExploreData
import com.release.keyneez.databinding.FragmentExploreRecentBinding
import com.release.keyneez.util.binding.BindingFragment

class RecentFragment :
    BindingFragment<FragmentExploreRecentBinding>(R.layout.fragment_explore_recent) {

    private val viewModel: RecentViewModel by viewModels()
    val data = mutableListOf<ExploreData>()
    private lateinit var RecentAdapter: RecentAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): RecentFragment {
            return RecentFragment()
        }
    }
}
