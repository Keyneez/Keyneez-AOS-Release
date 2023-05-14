package com.release.keyneez.presentation.main.explore

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.release.keyneez.presentation.main.explore.popular.PopularFragment
import com.release.keyneez.presentation.main.explore.recent.RecentFragment

class ExploreAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return PopularFragment()
            1 -> return RecentFragment()
        }
        return PopularFragment()
    }
}