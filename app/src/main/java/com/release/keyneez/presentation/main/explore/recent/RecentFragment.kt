package com.release.keyneez.presentation.main.explore.recent

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentRecentBinding
import com.release.keyneez.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentFragment : BindingFragment<FragmentRecentBinding>(R.layout.fragment_recent) {
    private val viewModel: RecentViewModel by viewModels()
    private var recentAdapter: RecentAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        initRecentAdapter()
        setupRecentActivityList()
        initCategoryBtnListener()
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
        if (filterValue != binding.tvExploreRecentAll.text.toString()) {
            viewModel.setFilterValue(filterValue)
            viewModel.getRecentData()
        } else {
            viewModel.setFilterValue("")
            viewModel.getRecentData()
        }
    }

    private fun initRecentAdapter() {
        recentAdapter = RecentAdapter()
        binding.rvExploreRecent.adapter = recentAdapter
    }

    private fun setupRecentActivityList() {
        viewModel.recentList.observe(viewLifecycleOwner) { recentList ->
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
