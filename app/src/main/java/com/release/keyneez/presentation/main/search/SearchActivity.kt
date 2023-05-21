package com.release.keyneez.presentation.main.search

import android.os.Bundle
import android.view.KeyEvent
import androidx.recyclerview.widget.GridLayoutManager
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
        initSearchAdapter()
        initSearchBtnKeyListener()
    }

    private fun initSearchBtnKeyListener() {
        binding.btnSearch.setOnKeyListener { v, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                viewModel.getSearchPostData()
                true
            } else {
                false
            }
        }
    }

    private fun initSearchAdapter() {
        searchAdapter = SearchAdapter()
        binding.rvSearchResultContent.adapter = searchAdapter
        binding.rvSearchResultContent.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initBackBtnClickListener() {
        binding.btnSearchCancel.setOnSingleClickListener {
            finish()
        }
    }
}
