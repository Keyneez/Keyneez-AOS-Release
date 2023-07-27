package com.release.keyneez.presentation.main.explore.recent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.release.keyneez.data.entity.response.ResponseGetRecentDto
import com.release.keyneez.data.repository.ContentRepository
import com.release.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {
    private val _recentList = MutableLiveData<List<ResponseGetRecentDto>>()
    val recentList: LiveData<List<ResponseGetRecentDto>>
        get() = _recentList

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    private val _saveState = MutableLiveData<List<Boolean>>()
    val saveState: LiveData<List<Boolean>>
        get() = _saveState

    val filter = MutableLiveData("")

    fun setFilterValue(value: String) {
        filter.value = value
    }

    fun clickLike(index: Int, isSelected: Boolean) {
        if (isSelected) {
            postUnLike(index)
            postUnLike(index)
            return
        }

        postSave(index)
    }

    fun getRecentData() {
        viewModelScope.launch {
            contentRepository.getRecent(filter.value.toString())
                .onSuccess { response ->
                    Timber.tag(successTag).d("response : $response")

                    if (response.data == null) {
                        Timber.d("GET RECENT CONTENT IS NULL")
                        _stateMessage.value = UiState.Failure(RECENT_DATA_NULL_CODE)
                    }
                    _recentList.value = response.data!!
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

    fun postSave(pk: Int) {
        viewModelScope.launch {
            contentRepository.postLike(pk).onSuccess { response ->

                Timber.d("POST SAVE STATE SUCCESS")
                Timber.d("response : $response")

                _stateMessage.value = UiState.Success
            }
                .onFailure {
                    Timber.d("throwable : $it")
                    _stateMessage.value = UiState.Error
                }
        }
    }

    fun postUnLike(pk: Int) {
        viewModelScope.launch {
            contentRepository.postUnlike(pk)
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

    companion object {
        const val RECENT_DATA_NULL_CODE = 100
        private const val successTag = "GET_RECENT_CONTENT_SUCCESS"
        private const val failTag = "GET_RECENT_CONTENT_FAIL"
    }
}
