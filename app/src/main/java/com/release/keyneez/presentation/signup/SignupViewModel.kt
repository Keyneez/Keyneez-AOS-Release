package com.release.keyneez.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SignupViewModel : ViewModel() {
    val nickname = MutableLiveData("")
}
