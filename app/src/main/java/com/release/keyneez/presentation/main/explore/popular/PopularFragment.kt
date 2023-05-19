package com.release.keyneez.presentation.main.explore.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentExplorePopularBinding
import com.release.keyneez.util.binding.BindingFragment

class PopularFragment :
    BindingFragment<FragmentExplorePopularBinding>(R.layout.fragment_explore_popular) {
    private var _binding: FragmentExplorePopularBinding? = null
        get() = _binding!!

    private val viewModel: PopularViewModel by viewModels()
    private lateinit var popularAdapter: PopularAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupExploreData()
        initPopularAdapter()
    }

    private fun setupExploreData() {
        viewModel.activityList.observe(viewLifecycleOwner) { activityList ->
            popularAdapter.data = activityList
            popularAdapter.notifyDataSetChanged()
        }
    }

    private fun initPopularAdapter() {
        popularAdapter = PopularAdapter()
        binding.rvExplorePopular.adapter = popularAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = PopularFragment()
    }
}
