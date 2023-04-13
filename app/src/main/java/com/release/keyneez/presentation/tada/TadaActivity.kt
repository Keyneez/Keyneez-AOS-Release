package com.release.keyneez.presentation.tada

import android.os.Bundle
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivityTadaBinding
import com.release.keyneez.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TadaActivity : BindingActivity<ActivityTadaBinding>(R.layout.activity_tada) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
