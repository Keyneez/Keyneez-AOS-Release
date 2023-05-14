package com.release.keyneez.presentation.main.explore.recent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.domain.model.ExploreData
import com.release.keyneez.databinding.FragmentExploreRecentBinding
import com.release.keyneez.presentation.main.explore.popular.PopularAdapter
import com.release.keyneez.util.binding.BindingFragment

class RecentFragment :
    BindingFragment<FragmentExploreRecentBinding>(R.layout.fragment_explore_recent) {

    private val viewModel: RecentViewModel by viewModels()
    val data = mutableListOf<ExploreData>()
    private lateinit var RecentAdapter: RecentAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecentData()
        initRecentAdapter()
    }

    private fun setupRecentData() {
        viewModel.itemList.observe(viewLifecycleOwner) {
            RecentAdapter.data = it
            RecentAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecentAdapter() {
        RecentAdapter = RecentAdapter()
        binding.rvExploreRecent.adapter = RecentAdapter
    }
    companion object {
        fun newInstance(): RecentFragment {
            return RecentFragment()
        }
    }
}
