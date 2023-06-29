package com.release.keyneez.util.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setVisible")
    fun View.setVisible(selected: Boolean) {
        isVisible = selected
    }

    @JvmStatic
    @BindingAdapter("setInvisible")
    fun View.setInvisible(selected: Boolean) {
        isInvisible = selected
    }

    @JvmStatic
    @BindingAdapter("setGone")
    fun View.setGone(selected: Boolean) {
        isGone = selected
    }

    @JvmStatic
    @BindingAdapter("isSelected")
    fun View.isSelected(selected: Boolean) {
        this.isSelected = selected
    }

    @JvmStatic
    @BindingAdapter("selectedEditSet", "editHashTag")
    fun TextView.setEditIndex(set: LiveData<LinkedHashSet<String>>, edit: String) {
        this.text = set.value?.indexOf(edit)?.plus(1).toString()
    }

    @JvmStatic
    @BindingAdapter("putStartDate", "putEndDate")
    fun TextView.setDuration(start: String?, end: String?) {
        if (start == null || end == null) {
            this.text = "2023 -"
            return
        }

        val tempStart = "${end.substring(4, 6)}.${end.substring(6, 8)}"
        val tempEnd = "${end.substring(4, 6)}.${end.substring(6, 8)}"

        this.text = "$tempStart - $tempEnd"
    }

    @JvmStatic
    @BindingAdapter("category")
    fun ImageView.card(string: String?) {
        when (string) {
            "문화" -> this.setImageResource(R.drawable.ic_home_card_mint)
            "진로" -> this.setImageResource(R.drawable.ic_home_card_green)
            "봉사" -> this.setImageResource(R.drawable.ic_home_card_purple)
            "여행" -> this.setImageResource(R.drawable.ic_home_card_pink)
            "경제" -> this.setImageResource(R.drawable.ic_home_card_red)
        }
    }
}
