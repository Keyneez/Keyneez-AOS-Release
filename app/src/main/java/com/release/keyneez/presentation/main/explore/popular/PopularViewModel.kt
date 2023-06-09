package com.release.keyneez.presentation.main.explore.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.release.keyneez.data.entity.response.ResponseGetContentDto
import com.release.keyneez.data.repository.ContentRepository
import com.release.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {
    private val _popularList = MutableLiveData<List<ResponseGetContentDto>>()
    val popularList: LiveData<List<ResponseGetContentDto>>
        get() = _popularList

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    private val _saveState = MutableLiveData<Boolean>()
    val saveState: LiveData<Boolean>
        get() = _saveState

    val filter = MutableLiveData("")

    init {
        getPopularList()
    }

    private fun getPopularList() {
        viewModelScope.launch {
            contentRepository.getContent(filter.value.toString())
                .onSuccess { response ->
                    Timber.tag(successTag).d("response : $response")

                    if (response.data == null) {
                        Timber.d("GET POPULAR CONTENT IS NULL")
                        _stateMessage.value = UiState.Failure(POPULAR_DATA_NULL_CODE)
                    }
                    _popularList.value = response.data!!
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
        const val POPULAR_DATA_NULL_CODE = 100
        private const val successTag = "GET_POPULAR_CONTENT_SUCCESS"
        private const val failTag = "GET_POPULAR_CONTENT_FAIL"
    }
}
