package com.release.keyneez.presentation.main.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.release.keyneez.R
import com.release.keyneez.domain.model.Activity

class LikeViewModel : ViewModel() {
    private val _activityList = MutableLiveData<List<Activity>>(mutableListOf())
    private val _isEdit = MutableLiveData<Boolean>()
    private val _isSelected = MutableLiveData<Boolean>()
    private val _selectedIds = MutableLiveData<MutableList<Int>>()
    val activityList: LiveData<List<Activity>>
        get() = _activityList
    val isEdit: LiveData<Boolean>
        get() = _isEdit
    val isSelected: LiveData<Boolean>
        get() = _isSelected
    val selectedIds: LiveData<MutableList<Int>> = _selectedIds

    init {
        getLikeActivityList()
        _isEdit.value = false
        _isSelected.value = false
        _selectedIds.value = emptyList<Int>().toMutableList()
    }

    fun setItemsSelected(id: Int): List<Int> {
        val selectedIdsList = _selectedIds.value ?: mutableListOf()
        if (selectedIdsList.contains(id)) {
            selectedIdsList.remove(id)
        } else {
            selectedIdsList.add(id)
        }
        _selectedIds.value = selectedIdsList
        return selectedIdsList.toList()
    }

    fun getSelectedIdsCount(id: Int): Int {
        val selectedIdsList = _selectedIds.value ?: mutableListOf()
        return selectedIdsList.size
    }

    fun updateEditView() {
        _isEdit.value = _isEdit.value?.not()
    }

    fun updateSelected() {
        _isSelected.value = _isSelected.value?.not()
    }
    fun deleteSelectedItems() {
        if (_isEdit.value == true) {
            val selectedIdsList = _selectedIds.value ?: return
            val updatedList = _activityList.value?.toMutableList() ?: mutableListOf()
            val positionsToRemove = mutableListOf<Int>()
            val selectedIdsSet = selectedIdsList.toSet()

            for (selectedId in selectedIdsList) {
                val itemToRemove = updatedList.find { it.id == selectedId }
                itemToRemove?.let {
                    val position = updatedList.indexOf(it)
                    positionsToRemove.add(position)
                    updatedList.remove(it)
                }
            }
            for (i in updatedList.size - 1 downTo 0) {
                val activity = updatedList[i]
                if (selectedIdsSet.contains(activity.id)) {
                    updatedList.removeAt(i)
                }
            }

            _activityList.value = updatedList.toList()
        }
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
                id = 2,
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            ),
            Activity(
                id = 3,
                background = R.drawable.img_explore_background,
                title = "행주산성\n맛집 투어",
                category = "진로",
                date = "%s-%s",
                liked = true
            ),
            Activity(
                id = 4,
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
