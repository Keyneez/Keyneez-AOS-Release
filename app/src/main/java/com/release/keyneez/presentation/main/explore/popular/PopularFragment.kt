package com.release.keyneez.presentation.main.explore.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentExplorePopularBinding
import com.release.keyneez.domain.model.ExploreData
import com.release.keyneez.util.binding.BindingFragment

class PopularFragment :
    BindingFragment<FragmentExplorePopularBinding>(R.layout.fragment_explore_popular) {

    private val viewModel: PopularViewModel by viewModels()
    val data = mutableListOf<ExploreData>()
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
        fun newInstance(): PopularFragment {
            return PopularFragment()
        }
    }
}
