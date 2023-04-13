package com.release.keyneez.presentation.main.search

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivitySearchBinding
import com.release.keyneez.util.binding.BindingActivity
import com.release.keyneez.util.extension.hideKeyboard
import com.release.keyneez.util.extension.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
    lateinit var searchAdapter: SearchAdapter
    private val viewModel by viewModels<SearchViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initSearchBtnClickListener()
        initHideKeyboard()
        initSearchBackBtnClickListener()
    }

    private fun initSearchBtnClickListener() {
        binding.btnSearchResult.setOnKeyListener { v, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                // 뷰모델 통신하는 코드 작성하기
                true
            } else {
                false
            }
        }
    }

    private fun initHideKeyboard() {
        binding.layoutSearchResult.setOnSingleClickListener {
            hideKeyboard()
        }
    }

    private fun initSearchBackBtnClickListener() {
        binding.btnSearchBack.setOnSingleClickListener {
            finish()
        }
    }
}
