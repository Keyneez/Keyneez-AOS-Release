package com.release.keyneez.presentation.main.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.release.keyneez.R
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.util.extension.notifyObserver

class LikeViewModel : ViewModel() {
    private val _activityList = MutableLiveData<List<Activity>>()
    private val _isEdit = MutableLiveData<Boolean>()
    private val _selectedEdits = MutableLiveData<LinkedHashSet<String>>()
    val activityList: LiveData<List<Activity>>
        get() = _activityList

    val isEdit: LiveData<Boolean>
        get() = _isEdit

    val selectedEdits: LiveData<LinkedHashSet<String>>
        get() = _selectedEdits

    init {
        getLikeActivityList()
        _isEdit.value = false
    }

    /** 편집화면으로 전환 **/
    fun updateEditView() {
        _isEdit.value = !requireNotNull(_isEdit.value)
    }

    fun selectEdits(edit: String) {
        // 이미 선택된 경우 삭제할 요소 제거
        if (_selectedEdits.value!!.contains(edit)) {
            _selectedEdits.value!!.remove(edit)
            _selectedEdits.notifyObserver()
            return
        }

        // 삭제할 요소 추가
        _selectedEdits.value!!.add(edit)
        _selectedEdits.notifyObserver()
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
