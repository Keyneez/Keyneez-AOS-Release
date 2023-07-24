package com.release.keyneez.presentation.main.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivitySearchBinding
import com.release.keyneez.util.UiState
import com.release.keyneez.util.binding.BindingActivity
import com.release.keyneez.util.extension.hideKeyboard
import com.release.keyneez.util.extension.setOnSingleClickListener
import com.release.keyneez.util.extension.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private var searchAdapter: SearchAdapter? = null
    private val viewModel: SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initSearchBtnClickListener()
        initSearchAdapter()
        initBackBtnClickListener()
        initSearchBtnKeyListener()
        setupSearchState()
    }

    private fun setupSearchState() {
        viewModel.stateMessage.observe(this) {
            when (it) {
                is UiState.Success -> setupSearchActivityList()
                is UiState.Failure -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_search_null)
                )

                is UiState.Error -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_server_error)
                )
            }
        }
    }

    private fun initSearchAdapter() {
        searchAdapter = SearchAdapter()
        binding.rvSearchResultContent.adapter = searchAdapter
    }

    private fun setupSearchActivityList() {
        viewModel.searchList.observe(this) { searchList ->
            searchAdapter?.submitList(searchList)
            binding.tvSearchCount.text = searchList?.size.toString()
            viewModel.updateSaveState(searchList.flatMap { it.Likes })
        }
    }

    private fun initSearchBtnKeyListener() {
        binding.etSearchContent.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    return true
                }
                return false
            }
        })
    }

    private fun initSearchBtnClickListener() {
        binding.btnSearch.setOnSingleClickListener {
            val searchKeyword = binding.etSearchContent.text.toString().trim()
            if(searchKeyword.isEmpty()){
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }
            if (searchKeyword.isNotEmpty()) {
                viewModel.updateCount()
                viewModel.getSearchPostData()
                hideKeyboard()
            }
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
