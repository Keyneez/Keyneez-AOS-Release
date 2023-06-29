package com.release.keyneez.util.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import coil.load
import com.release.keyneez.R

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
    @BindingAdapter("setImage")
    fun ImageView.setImage(url: String?) {
        this.load(url)
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
    @BindingAdapter("setCategory")
    fun TextView.setCategory(string: String?) {
        when (string) {
            "취미" ->
                this.background =
                    this.context.getDrawable(R.drawable.shape_mint500_fill_49_rect)

            "진로" -> this.background = this.context.getDrawable(R.drawable.shape_red500_fill_49_rect)
            "활동" ->
                this.background =
                    this.context.getDrawable(R.drawable.shape_purple500_fill_49_rect)
        }
    }
}
