package com.release.keyneez.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.repository.ContentRepository
import com.release.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {
    private val _searchList = MutableLiveData<List<ResponseGetSearchResultDto>>()
    val searchList: LiveData<List<ResponseGetSearchResultDto>>
        get() = _searchList

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    private val _isFlowVisible = MutableLiveData<Boolean>()
    val isFlowVisible: LiveData<Boolean>
        get() = _isFlowVisible

    private val _saveState = MutableLiveData<List<Boolean>>()
    val saveState: LiveData<List<Boolean>>
        get() = _saveState

    val key = MutableLiveData("")

    init {
        _isFlowVisible.value = false
    }

    fun updateCount() {
        _isFlowVisible.value = true
    }

    fun clickLike(index: Int, isSelected: Boolean) {
        if (isSelected) {
            postUnLike(index)
            return
        }

        postSave(index)
    }

    fun getSearchPostData() {
        viewModelScope.launch {
            contentRepository.getSearch(key.value.toString()).onSuccess { response ->
                if (response.data == null) {
                    Timber.d("GET SEARCH LIST IS NULL")
                    _stateMessage.value = UiState.Failure(SEARCH_NULL_CODE)
                    return@onSuccess
                }
                Timber.d("GET SEARCH LIST SUCCESS")
                Timber.d("response : $response")
                _searchList.value = response.data!!
                _stateMessage.value = UiState.Success
            }.onFailure {
                Timber.e("GET SEARCH LIST SERVER ERROR")
                Timber.e("message : ${it.message}")
                if (it is HttpException) {
                    Timber.e("response : $it")
                    when (it.code()) {
                        SEARCH_NO_POST_CODE ->
                            _stateMessage.value =
                                UiState.Failure(SEARCH_NO_POST_CODE)

                        else -> _stateMessage.value = UiState.Error
                    }
                } else _stateMessage.value = UiState.Error
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
            contentRepository.postUnlike(listOf(pk))
                .onSuccess { response ->
                    Timber.tag("POST UNLIKE STATE SUCCES뷰S")
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
        const val SEARCH_NULL_CODE = 100
        const val SEARCH_NO_POST_CODE = 404
    }
}
