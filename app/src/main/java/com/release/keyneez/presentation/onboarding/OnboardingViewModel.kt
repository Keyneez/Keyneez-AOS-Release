package com.release.keyneez.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnboardingViewModel : ViewModel() {
    private val _position: MutableLiveData<Int> = MutableLiveData()

    val positon: LiveData<Int>
        get() = _position

    fun setPosition(position: Int) {
        _position.value = position
    }
}
