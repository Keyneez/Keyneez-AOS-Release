package com.release.keyneez.presentation.main.explore.popular

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.release.keyneez.data.entity.response.ResponseGetPopularDto
import com.release.keyneez.databinding.FragmentPopularBinding
import com.release.keyneez.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment :
    BindingFragment<FragmentPopularBinding>(com.release.keyneez.R.layout.fragment_popular) {
    private val viewModel: PopularViewModel by viewModels()
    lateinit var list: List<ResponseGetPopularDto>
    private var popularAdapter: PopularAdapter? = null
    private var isInitialLoad = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPopularAdapter()
        setupPopularActivityList()
        initCategoryBtnListener()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        initCategoryBtnListener()
    }

    private fun initCategoryBtnListener() {
        selectOnlyOneButton(binding.tvExplorePopularAll)
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
        if (filterValue != binding.tvExplorePopularAll.text.toString()) {
            viewModel.setFilterValue(filterValue)
            viewModel.getPopularData()
        } else {
            viewModel.getAllPopularData()
        }
    }

    private fun initPopularAdapter() {
        popularAdapter = PopularAdapter(
            clickLike = viewModel::clickLike
        )
        binding.rvExplorePopular.adapter = popularAdapter
        val animator = binding.rvExplorePopular.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    private fun setupPopularActivityList() {
        viewModel.popularList.observe(viewLifecycleOwner) { popularList ->
            isInitialLoad = false
            list = popularList
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
