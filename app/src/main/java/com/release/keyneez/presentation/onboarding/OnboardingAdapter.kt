package com.release.keyneez.presentation.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf<Fragment>(
        OnFirstFragment(),
        OnSecondFragment(),
    )

    override fun createFragment(position: Int): Fragment = fragmentList[position]
    override fun getItemCount(): Int = fragmentList.size
}
