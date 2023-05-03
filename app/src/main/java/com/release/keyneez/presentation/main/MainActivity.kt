package com.release.keyneez.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivityMainBinding
import com.release.keyneez.presentation.main.explore.ExploreFragment
import com.release.keyneez.presentation.main.home.HomeFragment
import com.release.keyneez.presentation.main.like.LikeFragment
import com.release.keyneez.presentation.main.setting.SettingFragment
import com.release.keyneez.util.binding.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
