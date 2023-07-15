package com.release.keyneez.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    private val _isBnvVisible = MutableLiveData<Boolean>()
    val isBnvVisible: LiveData<Boolean>
        get() = _isBnvVisible

    init {
        _isBnvVisible.value = false
    }

    fun updateBnvView() {
        Log.d("1", "hollo")
        _isBnvVisible.value = _isBnvVisible.value?.not()
    }
}
