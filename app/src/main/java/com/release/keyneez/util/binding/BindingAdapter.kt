package com.release.keyneez.util.binding

import android.view.View
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.presentation.main.like.LikeAdapter

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
    @BindingAdapter("app:items")
    fun setList(recyclerView: RecyclerView, items: List<Activity>?) {
        items?.let {
            (recyclerView.adapter as LikeAdapter).likeList = items
        }
    }

    @JvmStatic
    @BindingAdapter("selectedEditSet", "editHashTag")
    fun TextView.setEditIndex(set: LiveData<LinkedHashSet<String>>, edit: String) {
        this.text = set.value?.indexOf(edit)?.plus(1).toString()
    }
}
