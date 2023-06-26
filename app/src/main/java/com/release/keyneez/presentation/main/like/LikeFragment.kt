package com.release.keyneez.presentation.main.like

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.release.keyneez.databinding.FragmentLikeBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.presentation.main.MainViewModel
import com.release.keyneez.presentation.main.explore.popular.PopularFragment
import com.release.keyneez.util.binding.BindingFragment
import com.release.keyneez.util.binding.BindingToast
import com.release.keyneez.util.extension.setOnSingleClickListener

class LikeFragment :
    BindingFragment<FragmentLikeBinding>(com.release.keyneez.R.layout.fragment_like) {
    private lateinit var dataList: ArrayList<Activity>
    private lateinit var likeAdapter: LikeAdapter
    private lateinit var mainViewModel: MainViewModel
    private val likeViewModel by viewModels<LikeViewModel>()
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEdit.isEnabled = false
        dataList = ArrayList()
        dataList.clear()
        binding.vm = likeViewModel
        binding.rvLike.apply {
            setHasFixedSize(true)
            val gridLayoutManager = GridLayoutManager(context,3)
            layoutManager = LinearLayoutManager(context)
            likeAdapter = LikeAdapter()
            adapter = likeAdapter
        }
        likeAdapter.setOnItemClickListener { response ->
            binding.btnEdit.isEnabled = likeAdapter.getSelectedExpense() > 0
        }
        likeAdapter.submitList(dataList)
        initLikeAdapter()
        initCategoryBtnClickListener()
        setupLikeActivityList()
        initLikeEditBtnClickListener()
        initEditBtnClickListener()
    }

//    fun updateDeleteItems() {
//        val selectedIdsList: LiveData<MutableList<Int>> = likeViewModel.selectedIds
//        selectedIdsList.observe(viewLifecycleOwner) { selectedIds ->
//            selectedIds?.clear()
//
//            for (item in selectedIds.orEmpty()) {
//                val position = likeList.indexOf(item)
//                binding.rvLike.adapter?.notifyItemRemoved(position)
//                binding.rvLike.adapter?.notifyItemRangeRemoved(position, likeList.size - 1)
//            }
//        }
//    }

    private fun initLikeAdapter() {
        likeAdapter = LikeAdapter(
//            setItemsSelected = likeViewModel::setItemsSelected,
//            getSelectedIdsCount = likeViewModel::getSelectedIdsCount
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
    }

    companion object {
        @JvmStatic
        fun newInstance() = PopularFragment()
    }
}
