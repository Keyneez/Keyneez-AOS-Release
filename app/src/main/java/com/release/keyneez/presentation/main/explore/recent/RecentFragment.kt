package com.release.keyneez.presentation.main.explore.recent

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentRecentBinding
import com.release.keyneez.util.binding.BindingFragment

class RecentFragment : BindingFragment<FragmentRecentBinding>(R.layout.fragment_recent) {
    private val viewModel: RecentViewModel by viewModels()
    private var recentAdapter: RecentAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecentAdapter()
        setupRecentActivityList()
        initCategoryBtnListener()
    }
    private fun initCategoryBtnListener() {
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
    }

    private fun initRecentAdapter() {
        recentAdapter = RecentAdapter()
        binding.rvExploreRecent.adapter = recentAdapter
    }

    private fun setupRecentActivityList() {
        viewModel.activityList.observe(viewLifecycleOwner) { activityList ->
            recentAdapter?.submitList(activityList)
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
