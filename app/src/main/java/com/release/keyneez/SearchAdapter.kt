package com.release.keyneez

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchAdapter {
    lateinit var searchAdapter: SearchAdapter
    private val viewModel by viewModels<SearchViewModel>()

}