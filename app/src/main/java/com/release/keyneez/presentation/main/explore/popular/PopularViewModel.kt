package com.release.keyneez.presentation.main.explore.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.release.keyneez.R
import com.release.keyneez.domain.model.Activity

class PopularViewModel : ViewModel() {
    private val _itemList = MutableLiveData<List<Activity>>()
    val itemList: LiveData<List<Activity>>
        get() = _itemList

    init {
        getPopularActivityList()
    }

    private fun getPopularActivityList() {
        val mainList = listOf(
            Activity(
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            ),
            Activity(
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            ),
            Activity(
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            ),
            Activity(
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
