package com.release.keyneez.presentation.main.like

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentLikeBinding
import com.release.keyneez.presentation.main.explore.popular.PopularFragment
import com.release.keyneez.util.binding.BindingFragment
import com.release.keyneez.util.extension.setOnSingleClickListener

class LikeFragment : BindingFragment<FragmentLikeBinding>(R.layout.fragment_like) {
    private val viewModel: LikeViewModel by viewModels()
    private var likeAdapter: LikeAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLikeAdapter()
        initCategoryBtnClickListener()
        setupLikeActivityList()
    }

    private fun initLikeAdapter() {
        likeAdapter = LikeAdapter()
        binding.rvLike.adapter = likeAdapter
    }
    private fun initCategoryBtnClickListener() {
        // ocr result 코드 참고하기
        binding.tvLikeAll.setOnSingleClickListener {}
        binding.tvLikeHobby.setOnSingleClickListener {}
        binding.tvLikeCareer.setOnSingleClickListener {}
        binding.tvLikeOutside.setOnSingleClickListener {}
    }

    private fun setupLikeActivityList() {
        viewModel.activityList.observe(viewLifecycleOwner) { activityList ->
            likeAdapter?.submitList(activityList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        likeAdapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = PopularFragment()
    }
}
