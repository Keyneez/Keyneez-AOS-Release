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

    // 뷰모델에 아이디 리스트를 저장하는 라이브데이터(여기에 id들을 저장)가 있어야 한다.
    // 삭제하기를 눌렀을 때 요청값으로 이 아이디 리스트를 보내 준다.
    // 인자로 인덱스를 받아오기
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
        // emptylist로 초기화하기!
        _selectedIds.value = emptyList<Int>().toMutableList()
    }

    // 아이디 리스트를 이용해 id가 있으면 제거하고 없으면 넣기
    // 그 후 id 서버에 넘겨주기
    fun setItemsSelected(id: Int): Int {
        val selectedIdsList = _selectedIds.value ?: mutableListOf()

        if (selectedIdsList.contains(id)) {
            selectedIdsList.remove(id)
        } else {
            selectedIdsList.add(id)
        }

        _selectedIds.value = selectedIdsList
        // 이게 맞나?
        return id
    }
    fun getSelectedIdsCount(id: Int): Int {
        val selectedIdsList = _selectedIds.value ?: mutableListOf()
        return selectedIdsList.size
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
