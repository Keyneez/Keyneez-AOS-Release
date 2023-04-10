package com.release.keyneez.util.extension

import android.view.View
import com.release.keyneez.util.OnSingleClickListener

fun View.setOnSingleClickListener(onSingleClick: (View) -> Unit) {
    val oneClick = OnSingleClickListener {
        onSingleClick(it)
    }
    setOnClickListener(oneClick)
}
