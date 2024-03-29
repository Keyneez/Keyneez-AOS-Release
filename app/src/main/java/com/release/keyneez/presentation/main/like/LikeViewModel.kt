package com.release.keyneez.presentation.main.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.data.repository.ContentRepository
import com.release.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {
    private val _likeList = MutableLiveData<List<ResponseGetLikeDto>>()
    private val _isEdit = MutableLiveData<Boolean>()
    private val _isSelected = MutableLiveData<Boolean>()
    private val _selectedIds = MutableLiveData<MutableList<Int>>()
    private val _selectedCount = MutableLiveData<Int>()
    private val _stateMessage = MutableLiveData<UiState>()
    private val _saveState = MutableLiveData<Boolean>()
    val selectedCount: LiveData<Int>
        get() = _selectedCount
    val likeList: LiveData<List<ResponseGetLikeDto>>
        get() = _likeList
    val isEdit: LiveData<Boolean>
        get() = _isEdit
    val isSelected: LiveData<Boolean>
        get() = _isSelected
    val selectedIds: LiveData<MutableList<Int>> = _selectedIds
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    val saveState: LiveData<Boolean>
        get() = _saveState

    val filter = MutableLiveData("")

    init {
        getSelectedIdsCount()
        _isEdit.value = false
        _isSelected.value = false
        _selectedIds.value = emptyList<Int>().toMutableList()
    }

    fun setFilterValue(value: String) {
        filter.value = value
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
        if (_isEdit.value == false) {
            _selectedIds.value?.clear()
            _selectedIds.value = mutableListOf()
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
        return selectedCount
    }

    fun deleteSelectedItems() {
        if (_isEdit.value == true) {
            val selectedIdsList = _selectedIds.value ?: return
            val updatedList = _likeList.value?.toMutableList() ?: mutableListOf()
            val positionsToRemove = mutableListOf<Int>()
            val selectedIdsSet = selectedIdsList.toSet()

            for (selectedId in selectedIdsList) {
                val itemToRemove = updatedList.find { it.content == selectedId }
                itemToRemove?.let {
                    val position = updatedList.indexOf(it)
                    positionsToRemove.add(position)
                    updatedList.remove(it)
                }
            }
            for (i in updatedList.size - 1 downTo 0) {
                val activity = updatedList[i]
                if (selectedIdsSet.contains(activity.content)) {
                    updatedList.removeAt(i)
                }
            }

            _likeList.value = updatedList.toList()
            viewModelScope.launch {
                contentRepository.postUnlike(selectedIdsList)
                    .onSuccess { response ->
                        Timber.tag("POST UNLIKE STATE SUCCESS")
                        Timber.d("response : $response")
                        _stateMessage.value = UiState.Success
                    }
                    .onFailure {
                        Timber.d("throwable : $it")
                        _stateMessage.value = UiState.Error
                    }
            }
        }
    }

    fun getLikeData() {
        viewModelScope.launch {
            contentRepository.getLike(filter.value.toString())
                .onSuccess { response ->
                    Timber.tag(successTag).d("response : $response")

                    if (response.data == null) {
                        Timber.d("GET LIKE CONTENT IS NULL")
                        _stateMessage.value = UiState.Failure(LIKE_DATA_NULL_CODE)
                    }
                    _likeList.value = response.data!!
                    _stateMessage.value = UiState.Success
                }
                .onFailure {
                    Timber.tag(failTag).e("throwable : $it")
                    if (it is HttpException) {
                        Timber.tag(failTag).e("code : ${it.code()}")
                        Timber.tag(failTag).e("message : ${it.message()}")
                    }
                }
        }
    }

    fun getAllLikeData() {
        viewModelScope.launch {
            contentRepository.getAllLike()
                .onSuccess { response ->
                    Timber.tag(successTag).d("response : $response")

                    if (response.data == null) {
                        Timber.d("GET LIKE CONTENT IS NULL")
                        _stateMessage.value = UiState.Failure(LIKE_DATA_NULL_CODE)
                    }
                    _likeList.value = response.data!!
                    _stateMessage.value = UiState.Success
                }
                .onFailure {
                    Timber.tag(failTag).e("throwable : $it")
                    if (it is HttpException) {
                        Timber.tag(failTag).e("code : ${it.code()}")
                        Timber.tag(failTag).e("message : ${it.message()}")
                    }
                }
        }
    }

    companion object {
        const val LIKE_DATA_NULL_CODE = 100
        private const val successTag = "GET_LIKE_CONTENT_SUCCESS"
        private const val failTag = "GET_LIKE_CONTENT_FAIL"
    }
}
