package com.release.keyneez.presentation.main.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.release.keyneez.R
import com.release.keyneez.domain.model.Activity

class LikeViewModel : ViewModel() {
    private val _activityList = MutableLiveData<List<Activity>>(mutableListOf())
    private val _isEdit = MutableLiveData<Boolean>()

    // 이게 맞나..?
    val editsList = mutableListOf<Activity>()
    val activityList: LiveData<List<Activity>>
        get() = _activityList

    val isEdit: LiveData<Boolean>
        get() = _isEdit

    private val deletedItemsCount = MutableLiveData<MutableList<Int>>()

    private val _isDeleted = MutableLiveData<MutableList<Boolean>>()
    val isDeleted: LiveData<MutableList<Boolean>> = _isDeleted

    init {
        getLikeActivityList()
        _isEdit.value = false
        // 이 부분을 여기에 넣는 게 맞나..?
        _isDeleted.value = MutableList(editsList.size) { true }
        deletedItemsCount.value = MutableList(editsList.size) { 1 }
    }

    fun setDeletedItemsCount(index: Int): Int {
        return deletedItemsCount.value!![index]
    }

    fun setItemsSelectedTrue() {
        _isDeleted.value = MutableList(_activityList.value!!.size) { true }
    }

    fun setItemsSelectedFalse() {
        _isDeleted.value = MutableList(_activityList.value!!.size) { false }
    }

    fun setItemsCheckBoxSelected(index: Int): Boolean {
        return _isDeleted.value!![index]
    }

    fun ItemOnClick(index: Int, selected: Boolean) {
        _isDeleted.value!![index] = selected
    }

    /** 편집화면으로 전환 **/
    fun updateEditView() {
        _isEdit.value = !requireNotNull(_isEdit.value)
    }

    private fun getLikeActivityList() {
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
