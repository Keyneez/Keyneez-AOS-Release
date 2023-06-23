package com.release.keyneez.presentation.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivityMainBinding
import com.release.keyneez.presentation.main.explore.ExploreFragment
import com.release.keyneez.presentation.main.home.HomeFragment
import com.release.keyneez.presentation.main.like.LikeFragment
import com.release.keyneez.presentation.main.like.LikeViewModel
import com.release.keyneez.presentation.main.setting.SettingFragment
import com.release.keyneez.util.binding.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    val likeViewModel by viewModels<LikeViewModel>()
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.vm = likeViewModel
        initBnvItemSelectedListener()
        updateHideBnv()
    }

    private fun initBnvItemSelectedListener() {
        supportFragmentManager.findFragmentById(R.id.fcv_main) ?: navigateTo<HomeFragment>()

        binding.bnvMain.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_main_home -> navigateTo<HomeFragment>()
                R.id.menu_main_explore -> navigateTo<ExploreFragment>()
                R.id.menu_main_like -> navigateTo<LikeFragment>()
                R.id.menu_main_setting -> navigateTo<SettingFragment>()
            }
            true
        }
    }

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace<T>(R.id.fcv_main, T::class.java.canonicalName)
        }
    }

    fun updateHideBnv() {
        // Observe the visibility state of the bottom navigation
        mainViewModel.isBnvVisible.observe(this, { isVisible ->
            if (isVisible) {
                binding.bnvMain.visibility = View.VISIBLE
            } else {
                binding.bnvMain.visibility = View.GONE
            }
        })
    }
}
