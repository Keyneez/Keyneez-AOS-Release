package com.release.keyneez.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.common.util.Utility
import com.release.keyneez.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        val keyHash = Utility.getKeyHash(this)
        Timber.tag("Hash").d(keyHash)
    }
}
