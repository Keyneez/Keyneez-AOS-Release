package com.release.keyneez.presentation.main.explore

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentExploreBinding
import com.release.keyneez.util.binding.BindingFragment

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initExploreViewPagerAdapter()
    }

    private fun initExploreViewPagerAdapter() {
        val viewPager = binding.vpExplore
        val tabLayout = binding.tabExplore

        val homeTabTitles = listOf(
            getString(R.string.explore_popular),
            getString(R.string.explore_recent)
        )

        viewPager.adapter = ExploreAdapter(parentFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = homeTabTitles[position]
        }.attach()
    }

    companion object {
        fun newInstance(): ExploreFragment {
            return ExploreFragment()
        }
    }
}
