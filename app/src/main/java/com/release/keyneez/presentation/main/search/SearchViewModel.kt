package com.release.keyneez.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.release.keyneez.R
import com.release.keyneez.domain.model.Activity

class SearchViewModel : ViewModel() {
    private val _activityList = MutableLiveData<List<Activity>>()
    val activityList: LiveData<List<Activity>>
        get() = _activityList

    init {
        getSearchActivityList()
    }

    private fun getSearchActivityList() {
        val mainList = listOf(
            Activity(
                id = 1,
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            ),
            Activity(
                id = 1,
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            ),
            Activity(
                id = 1,
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            ),
            Activity(
                id = 1,
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            )
        )
        _activityList.value = mainList
    }
}
