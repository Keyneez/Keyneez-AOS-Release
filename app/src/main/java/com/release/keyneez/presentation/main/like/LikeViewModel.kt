package com.release.keyneez.presentation.main.like

import android.util.Log
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
    private val _selectedCount = MutableLiveData<Int>()
    val selectedCount: LiveData<Int>
        get() = _selectedCount
    val activityList: LiveData<List<Activity>>
        get() = _activityList
    val isEdit: LiveData<Boolean>
        get() = _isEdit
    val isSelected: LiveData<Boolean>
        get() = _isSelected
    val selectedIds: LiveData<MutableList<Int>> = _selectedIds

    init {
        getLikeActivityList()
        getSelectedIdsCount()
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
        getSelectedIdsCount()
        return selectedIdsList.toList()
    }

    fun clearSelectedItems() {
        Log.d("1", "제발")
        if (_isEdit.value == false) {
            _selectedIds.value?.clear()
            getSelectedIdsCount()
        }
    }

    fun updateEditView() {
        _isEdit.value = _isEdit.value?.not()
    }

    fun getSelectedIdsCount(): Int {
        val selectedIdsList = _selectedIds.value ?: mutableListOf()
        val selectedCount = selectedIdsList.size
        _selectedCount.value = selectedCount
        return selectedIdsList.size
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
