package com.release.keyneez.presentation.main.explore.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentExplorePopularBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.util.binding.BindingFragment

class PopularFragment :
    BindingFragment<FragmentExplorePopularBinding>(R.layout.fragment_explore_popular) {

    private val viewModel: PopularViewModel by viewModels()
    val data = mutableListOf<Activity>()
    private lateinit var PopularAdapter: PopularAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupExploreData()
        initPopularAdapter()
    }

    private fun setupExploreData() {
        viewModel.itemList.observe(viewLifecycleOwner) {
            PopularAdapter.data = it
            PopularAdapter.notifyDataSetChanged()
        }
    }

    private fun initPopularAdapter() {
        PopularAdapter = PopularAdapter()
        binding.rvExplorePopular.adapter = PopularAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = PopularFragment()
    }
}
