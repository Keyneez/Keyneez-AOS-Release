package com.release.keyneez.presentation.main.explore.popular

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentPopularBinding
import com.release.keyneez.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment :
    BindingFragment<FragmentPopularBinding>(com.release.keyneez.R.layout.fragment_popular) {
    private val viewModel: PopularViewModel by viewModels()
    private var popularAdapter: PopularAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.tvExplorePopularAll.setOnClickListener {
            binding.tvExplorePopularAll.isSelected = !binding.tvExplorePopularAll.isSelected
        }
        initPopularAdapter()
        setupPopularActivityList()
    }

    private fun initPopularAdapter() {
        popularAdapter = PopularAdapter()
        binding.rvExplorePopular.adapter = popularAdapter
    }

    private fun setupPopularActivityList() {
        viewModel.popularList.observe(viewLifecycleOwner) { popularList ->
            popularAdapter?.submitList(popularList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        popularAdapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = PopularFragment()
    }
}
