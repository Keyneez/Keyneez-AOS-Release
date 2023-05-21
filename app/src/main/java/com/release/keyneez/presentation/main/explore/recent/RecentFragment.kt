package com.release.keyneez.presentation.main.explore.recent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentRecentBinding
import com.release.keyneez.util.binding.BindingFragment

class RecentFragment : BindingFragment<FragmentRecentBinding>(R.layout.fragment_recent) {
    private val viewModel: RecentViewModel by viewModels()
    private var recentAdapter: RecentAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecentAdapter()
        setupRecentActivityList()
    }

    private fun initRecentAdapter() {
        recentAdapter = RecentAdapter()
        binding.rvExploreRecent.adapter = recentAdapter
    }

    private fun setupRecentActivityList() {
        viewModel.activityList.observe(viewLifecycleOwner) { activityList ->
            recentAdapter?.submitList(activityList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recentAdapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecentFragment()
    }
}
