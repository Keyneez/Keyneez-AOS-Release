package com.release.keyneez.presentation.main.explore

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentExploreBinding
import com.release.keyneez.presentation.main.search.SearchActivity
import com.release.keyneez.util.binding.BindingFragment
import com.release.keyneez.util.extension.setOnSingleClickListener

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initExploreViewPagerAdapter()
        initSearchClickListener()
    }

    private fun initExploreViewPagerAdapter() {
        val viewPager = binding.vpExplore
        val tabLayout = binding.tabExplore

        val exploreTabTitles = listOf(
            getString(R.string.explore_popular),
            getString(R.string.explore_recent)
        )

        viewPager.adapter = ExploreAdapter(parentFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = exploreTabTitles[position]
        }.attach()
    }

    private fun initSearchClickListener() {
        binding.btnExploreSearch.setOnSingleClickListener {
            val intent = Intent(getActivity(), SearchActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(): ExploreFragment {
            return ExploreFragment()
        }
    }
}
