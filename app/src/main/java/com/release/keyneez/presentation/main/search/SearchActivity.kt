package com.release.keyneez.presentation.main.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivitySearchBinding
import com.release.keyneez.util.binding.BindingActivity
import com.release.keyneez.util.extension.setOnSingleClickListener
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
        initSearchBtnClickListener()
        initSearchAdapter()
        initBackBtnClickListener()
        initSearchBtnKeyListener()
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
            viewModel.activityList
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

    private fun initSearchAdapter() {
        searchAdapter = SearchAdapter()
        binding.rvSearchResultContent.adapter = searchAdapter
    }

    private fun setupSearchActivityList() {
        viewModel.activityList.observe(this) { activityList ->
            searchAdapter?.submitList(activityList)
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
