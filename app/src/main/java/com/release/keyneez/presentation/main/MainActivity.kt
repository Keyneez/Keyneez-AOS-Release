package com.release.keyneez.presentation.main

import android.os.Bundle
import com.kakao.sdk.common.util.Utility
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivityMainBinding
import com.release.keyneez.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val keyHash = Utility.getKeyHash(this)
        Timber.tag("Hash").d(keyHash)
    }
}
