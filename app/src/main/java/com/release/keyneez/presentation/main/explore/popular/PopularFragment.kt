package com.release.keyneez.presentation.main.explore.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentPopularBinding
import com.release.keyneez.util.binding.BindingFragment

class PopularFragment : BindingFragment<FragmentPopularBinding>(R.layout.fragment_popular) {
    private val viewModel: PopularViewModel by viewModels()
    private var popularAdapter: PopularAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPopularAdapter()
        setupPopularActivityList()
    }

    private fun initPopularAdapter() {
        popularAdapter = PopularAdapter()
        binding.rvExplorePopular.adapter = popularAdapter
    }

    private fun setupPopularActivityList() {
        viewModel.activityList.observe(viewLifecycleOwner) { activityList ->
            popularAdapter?.submitList(activityList)
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
