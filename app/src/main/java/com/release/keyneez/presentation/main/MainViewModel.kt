package com.release.keyneez.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    private val _isBnvVisible = MutableLiveData(true)
    val isBnvVisible: LiveData<Boolean>
        get() = _isBnvVisible

    fun showBottomNavigation() {
        _isBnvVisible.value = true
    }

    fun hideBottomNavigation() {
        _isBnvVisible.value = false
    }
}
