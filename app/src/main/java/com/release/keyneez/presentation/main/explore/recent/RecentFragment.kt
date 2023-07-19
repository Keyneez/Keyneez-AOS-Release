package com.release.keyneez.presentation.main.explore.recent

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.release.keyneez.R
import com.release.keyneez.data.entity.response.ResponseGetContentDto
import com.release.keyneez.databinding.FragmentRecentBinding
import com.release.keyneez.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentFragment : BindingFragment<FragmentRecentBinding>(R.layout.fragment_recent) {
    private val viewModel: RecentViewModel by viewModels()
    private var recentAdapter: RecentAdapter? = null
    lateinit var list: List<ResponseGetContentDto>
    private var isInitialLoad = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        initRecentAdapter()
        setupRecentActivityList()
        initCategoryBtnListener()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    private fun initCategoryBtnListener() {
        selectOnlyOneButton(binding.tvExploreRecentAll)
        binding.tvExploreRecentAll.setOnClickListener {
            selectOnlyOneButton(binding.tvExploreRecentAll)
        }
        binding.tvExploreRecentCareer.setOnClickListener {
            selectOnlyOneButton(binding.tvExploreRecentCareer)
        }
        binding.tvExploreRecentHobby.setOnClickListener {
            selectOnlyOneButton(binding.tvExploreRecentHobby)
        }
        binding.tvExploreRecentOutside.setOnClickListener {
            selectOnlyOneButton(binding.tvExploreRecentOutside)
        }
    }

    private fun selectOnlyOneButton(selectedButton: TextView) {
        binding.tvExploreRecentAll.isSelected = false
        binding.tvExploreRecentCareer.isSelected = false
        binding.tvExploreRecentHobby.isSelected = false
        binding.tvExploreRecentOutside.isSelected = false

        selectedButton.isSelected = true
        val filterValue = selectedButton.text.toString()
        if (isInitialLoad == false) {
            if (filterValue != binding.tvExploreRecentAll.text.toString()) {
                viewModel.setFilterValue(filterValue)
                viewModel.getRecentData()
            } else {
                viewModel.setFilterValue("")
                viewModel.getRecentData()
            }
        }
    }

    private fun initRecentAdapter() {
        recentAdapter = RecentAdapter()
        binding.rvExploreRecent.adapter = recentAdapter
        val animator = binding.rvExploreRecent.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    private fun setupRecentActivityList() {
        viewModel.recentList.observe(viewLifecycleOwner) { recentList ->
            isInitialLoad = false
            list = recentList
            recentAdapter?.submitList(recentList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recentAdapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecentFragment()
    }
}
