package com.release.keyneez.presentation.main.explore

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.release.keyneez.presentation.main.explore.popular.PopularFragment
import com.release.keyneez.presentation.main.explore.recent.RecentFragment
import javax.annotation.Nonnull

class ExploreAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    @Override
    override fun getItemCount(): Int {
        return 2
    }

    @Override
    @Nonnull
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return PopularFragment()
            1 -> return RecentFragment()
        }
        return PopularFragment()
    }
}
