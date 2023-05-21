package com.release.keyneez.presentation.main.search

import android.os.Bundle
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivitySearchBinding
import com.release.keyneez.util.binding.BindingActivity
import com.release.keyneez.util.extension.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private lateinit var searchAdapter: SearchAdapter

    // 뷰모델 관련 기초 코드 작성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBackBtnClickListener()
    }

    private fun searchBtnKeyListener() {
//        binding.btnSearch.setOnKeyListener {}
    }

    private fun initBackBtnClickListener() {
        binding.btnSearchCancel.setOnSingleClickListener {
            finish()
        }
    }
}
