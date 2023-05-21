package com.release.keyneez.presentation.main.explore.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentPopularBinding
import com.release.keyneez.util.binding.BindingFragment
import com.release.keyneez.util.extension.setOnSingleClickListener

class PopularFragment : BindingFragment<FragmentPopularBinding>(R.layout.fragment_popular) {
    private val viewModel: PopularViewModel by viewModels()
    private var popularAdapter: PopularAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPopularAdapter()
        setupPopularActivityList()
        initCategoryBtnClickListener()
    }

    private fun initPopularAdapter() {
        popularAdapter = PopularAdapter()
        binding.rvExplorePopular.adapter = popularAdapter
    }

    private fun initCategoryBtnClickListener() {
        // ocr result 코드 참고하기
        binding.tvExplorePopularAll.setOnSingleClickListener {}
        binding.tvExplorePopularHobby.setOnSingleClickListener {}
        binding.tvExplorePopularCareer.setOnSingleClickListener {}
        binding.tvExplorePopularOutside.setOnSingleClickListener {}
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
