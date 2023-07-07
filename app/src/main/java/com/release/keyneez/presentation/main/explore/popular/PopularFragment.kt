package com.release.keyneez.presentation.main.explore.popular

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
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
        initPopularAdapter()
        setupPopularActivityList()
        initCategoryBtnListener()
    }

    private fun initCategoryBtnListener() {
        binding.tvExplorePopularAll.setOnClickListener {
            selectOnlyOneButton(binding.tvExplorePopularAll)
        }
        binding.tvExplorePopularCareer.setOnClickListener {
            selectOnlyOneButton(binding.tvExplorePopularCareer)
        }
        binding.tvExplorePopularHobby.setOnClickListener {
            selectOnlyOneButton(binding.tvExplorePopularHobby)
        }
        binding.tvExplorePopularOutside.setOnClickListener {
            selectOnlyOneButton(binding.tvExplorePopularOutside)
        }
    }

    private fun selectOnlyOneButton(selectedButton: TextView) {
        binding.tvExplorePopularAll.isSelected = false
        binding.tvExplorePopularCareer.isSelected = false
        binding.tvExplorePopularHobby.isSelected = false
        binding.tvExplorePopularOutside.isSelected = false

        selectedButton.isSelected = true
        val filterValue = selectedButton.text.toString()
        viewModel.setFilterValue(filterValue)
        viewModel.getPopularData()
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
