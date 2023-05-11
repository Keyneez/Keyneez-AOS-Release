package com.release.keyneez.presentation.main.explore.recent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.release.keyneez.R
import com.release.keyneez.domain.model.ExploreData

class RecentViewModel : ViewModel() {
    private val _itemList = MutableLiveData<List<ExploreData>>()
    val itemList: LiveData<List<ExploreData>>
        get() = _itemList

    init {
        getRecentItemList()
    }

    private fun getRecentItemList() {
        val mainList = listOf(
            ExploreData(
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            ),
            ExploreData(
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            ),
            ExploreData(
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            ),
            ExploreData(
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            )
        )
        _itemList.value = mainList
    }
}
