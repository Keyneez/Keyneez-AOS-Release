package com.release.keyneez.presentation.main.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivitySearchBinding
import com.release.keyneez.util.binding.BindingActivity
import com.release.keyneez.util.extension.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private var searchAdapter: SearchAdapter? = null
    private val viewModel: SearchViewModel by viewModels()

    // 뷰모델 관련 기초 코드 작성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBackBtnClickListener()
        initSearchAdapter()
        initSearchBtnKeyListener()
        setupSearchActivityList()
    }

    private fun initSearchBtnKeyListener() {
        binding.btnSearch.setOnKeyListener { v, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                viewModel.activityList
                true
            } else {
                false
            }
        }

        binding.etSearchContent.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.activityList
                    return true
                }
                return false
            }
        })
    }

    private fun initSearchAdapter() {
        searchAdapter = SearchAdapter()
        binding.rvSearchResultContent.adapter = searchAdapter
        binding.rvSearchResultContent.layoutManager = GridLayoutManager(this, 2)
    }

    private fun setupSearchActivityList() {
        viewModel.activityList.observe(this) { activityList ->
            searchAdapter?.submitList(activityList)
//            binding.tvSearchCount.setText(searchAdapter?.data?.size.toString())
//            if (searchAdapter?.data?.size == 0) {
//                showToast(getString(R.string.search_no_result))
//            }
        }
    }

    private fun initBackBtnClickListener() {
        binding.btnSearchCancel.setOnSingleClickListener {
            finish()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchActivity()
    }
}
