package com.release.keyneez.presentation.main.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivitySearchBinding
import com.release.keyneez.util.UiState
import com.release.keyneez.util.binding.BindingActivity
import com.release.keyneez.util.extension.hideKeyboard
import com.release.keyneez.util.extension.setOnSingleClickListener
import com.release.keyneez.util.extension.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private var searchAdapter: SearchAdapter? = null
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.flowSearch.visibility = View.GONE
        binding.tvSearchEnd.visibility = View.GONE
        initSearchBtnClickListener()
        initSearchAdapter()
        initBackBtnClickListener()
        initSearchBtnKeyListener()
        initHideKeyboard()
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
        viewModel.searchList.observe(this) {
            val searchList = viewModel.searchList.value
            searchAdapter?.submitList(searchList)
            binding.tvSearchCount.text = searchList?.size.toString()
        }
    }

    private fun initHideKeyboard() {
        binding.layoutSearch.setOnSingleClickListener {
            hideKeyboard()
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
            setupSearchActivityList()
            Log.d("1", "제바루ㅜ")
            viewModel.updateCount()
        }
    }

    private fun debounce(): kotlinx.coroutines.flow.Flow<Int> = flow<Int> {
        emit(1)
        emit(2)
        delay(500L)
        emit(3)
        emit(4)
        delay(200L)
        emit(5)
        delay(700L)
        emit(6)
    }.debounce(300L)

    fun main() = runBlocking<Unit> {
        debounce().collect { }
    }

    fun <T> debounce(
        timeMillis: Long = 300L,
        coroutineScope: CoroutineScope,
        block: (T) -> Unit
    ): (T) -> Unit {
        var debounceJob: Job? = null
        return {
            debounceJob?.cancel()
            debounceJob = coroutineScope.launch {
                delay(timeMillis)
                block(it)
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
