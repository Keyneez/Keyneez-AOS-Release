package com.release.keyneez.presentation.main.explore

import ExploreAdapter
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
        with(binding) {
            val exploreTabTitles = listOf(
                getString(R.string.explore_popular),
                getString(R.string.explore_recent)
            )
            vpExplore.adapter = ExploreAdapter(requireActivity())
            TabLayoutMediator(tabExplore, vpExplore) { tab, position ->
                tab.text = exploreTabTitles[position]
            }.attach()
        }
    }

    private fun initSearchClickListener() {
        binding.btnExploreSearch.setOnSingleClickListener {
            startActivity(Intent(activity, SearchActivity::class.java))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExploreFragment()
    }
}
