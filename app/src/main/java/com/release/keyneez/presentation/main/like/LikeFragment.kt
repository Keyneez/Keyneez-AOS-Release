package com.release.keyneez.presentation.main.like

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.SimpleItemAnimator
import com.release.keyneez.databinding.FragmentLikeBinding
import com.release.keyneez.presentation.main.MainViewModel
import com.release.keyneez.presentation.main.explore.popular.PopularFragment
import com.release.keyneez.util.binding.BindingFragment
import com.release.keyneez.util.binding.BindingToast
import com.release.keyneez.util.extension.setOnSingleClickListener

class LikeFragment :
    BindingFragment<FragmentLikeBinding>(com.release.keyneez.R.layout.fragment_like) {
    val likeViewModel by viewModels<LikeViewModel>()
    private var likeAdapter: LikeAdapter? = null
    private lateinit var mainViewModel: MainViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = likeViewModel
        initLikeAdapter()
        initCategoryBtnClickListener()
        setupLikeActivityList()
        initLikeEditBtnClickListener()
        initEditBtnClickListener()
    }

    private fun initLikeAdapter() {
        likeAdapter = LikeAdapter(
            setItemsSelected = likeViewModel::setItemsSelected,
            getSelectedIdsCount = likeViewModel::getSelectedIdsCount
        )
        binding.rvLike.adapter = likeAdapter
        val animator = binding.rvLike.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
        likeViewModel.activityList.observe(viewLifecycleOwner) { activityList ->
            likeAdapter?.submitList(activityList)
        }
    }

    private fun setItemsSelected(id: Int) {
        likeViewModel.setItemsSelected(id)
        likeAdapter?.notifyItemChanged(id)
    }

    private fun initLikeEditBtnClickListener() {
        binding.btnLikeEdit.setOnSingleClickListener {
            mainViewModel.hideBottomNavigation()
        }
    }

    private fun initEditBtnClickListener() {
        binding.btnEdit.setOnSingleClickListener {
            likeViewModel.activityList.observe(
                viewLifecycleOwner,
                Observer {
                    it?.let {
                        likeAdapter?.submitList(it)
                    }
                }
            )
            BindingToast.initLikeDeleteToast(
                requireContext(),
                getString(com.release.keyneez.R.string.like_delete_complete)
            )?.show()
            mainViewModel.showBottomNavigation()
        }
    }

    private fun initCategoryBtnClickListener() {
        binding.tvLikeAll.setOnSingleClickListener {}
        binding.tvLikeHobby.setOnSingleClickListener {}
        binding.tvLikeCareer.setOnSingleClickListener {}
        binding.tvLikeOutside.setOnSingleClickListener {}
    }

    private fun setupLikeActivityList() {
        likeViewModel.activityList.observe(viewLifecycleOwner) { activityList ->
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
