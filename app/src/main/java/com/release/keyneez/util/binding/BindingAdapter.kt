package com.release.keyneez.util.binding

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

object BindingAdapter {
    @JvmStatic
    @androidx.databinding.BindingAdapter("setVisible")
    fun View.setVisible(selected: Boolean) {
        isVisible = selected
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setInvisible")
    fun View.setInvisible(selected: Boolean) {
        isInvisible = selected
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setGone")
    fun View.setGone(selected: Boolean) {
        isGone = selected
    }
}
