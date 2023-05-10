package com.release.keyneez.presentation.main.explore.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.data.entity.ExploreData
import com.release.keyneez.databinding.FragmentExplorePopularBinding
import com.release.keyneez.presentation.main.explore.recent.RecentViewModel
import com.release.keyneez.util.binding.BindingFragment

class PopularFragment :
    BindingFragment<FragmentExplorePopularBinding>(R.layout.fragment_explore_popular) {

    private val viewModel: RecentViewModel by viewModels()
    val data = mutableListOf<ExploreData>()
    private lateinit var PolularAdapter: PopularAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): PopularFragment {
            return PopularFragment()
        }
    }
}
