package com.release.keyneez.presentation.main

import LikeFragment
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivityMainBinding
import com.release.keyneez.presentation.main.explore.ExploreFragment
import com.release.keyneez.presentation.main.home.HomeFragment
import com.release.keyneez.presentation.main.like.LikeViewModel
import com.release.keyneez.presentation.main.setting.SettingFragment
import com.release.keyneez.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    val likeViewModel by viewModels<LikeViewModel>()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.vm = mainViewModel

        mainViewModel.isBnvVisible.observe(
            this,
            Observer { isVisible ->
                if (isVisible) {
                    // 바텀네비게이션을 표시
                    binding.bnvMain.visibility = View.VISIBLE
                } else {
                    // 바텀네비게이션을 숨김
                    binding.bnvMain.visibility = View.GONE
                }
            }
        )
        initBnvItemSelectedListener()
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
}
