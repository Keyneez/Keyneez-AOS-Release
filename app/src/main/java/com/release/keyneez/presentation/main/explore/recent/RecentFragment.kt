package com.release.keyneez.presentation.main.explore.recent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentExploreRecentBinding
import com.release.keyneez.util.binding.BindingFragment

class RecentFragment :
    BindingFragment<FragmentExploreRecentBinding>(R.layout.fragment_explore_recent) {
    private var _binding: FragmentExploreRecentBinding? = null
        get() = _binding!!

    private val viewModel: RecentViewModel by viewModels()
    private lateinit var recentAdapter: RecentAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecentData()
        initRecentAdapter()
    }

    private fun setupRecentData() {
        viewModel.activityList.observe(viewLifecycleOwner) { activityList ->
            recentAdapter.data = activityList
            recentAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecentAdapter() {
        recentAdapter = RecentAdapter()
        binding.rvExploreRecent.adapter = recentAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecentFragment()
    }
}
