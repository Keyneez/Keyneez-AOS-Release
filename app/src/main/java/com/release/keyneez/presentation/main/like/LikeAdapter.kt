package com.release.keyneez.presentation.main.like

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.databinding.ItemLikeContentBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.util.DiffCallback
import com.release.keyneez.util.extension.setOnSingleClickListener

class LikeAdapter(
    private val setItemsSelected: (Int) -> List<Int>,
    private val getSelectedIdsCount: (Int) -> Int
) : ListAdapter<Activity, RecyclerView.ViewHolder>(diffUtil) {
    private var selectedActivity = arrayListOf<Activity>()

    inner class LikeViewHolder(
        private val binding: ItemLikeContentBinding,
        private val setItemsSelected: (Int) -> List<Int>,
        private val getSelectedIdsCount: (Int) -> Int
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Activity) {
            with(binding) {
                data = item
                ivLikeBackground.setOnSingleClickListener {
                    // isSelcted 여부를 반대로
                    item.isSelected = !item.isSelected
                    // id 만 넘겨주는 함수 호출
                    setItemsSelected(item.id)
                    ivLikeCheckedBackground.visibility = if (item.isSelected) View.VISIBLE else View.GONE
                    btnLikeChecked.visibility = if (item.isSelected) View.VISIBLE else View.GONE
                }
            }
        }
    }

    fun getSelectedExpense() = selectedActivity.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLikeContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LikeViewHolder(
            binding,
            setItemsSelected,
            getSelectedIdsCount
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LikeViewHolder) holder.bind(getItem(position))
    }

    private var onItemClickListener: ((Activity) -> Unit)? = null

    companion object {
        private val diffUtil =
            DiffCallback<Activity>(
                onItemsTheSame = { old, new -> old.id == new.id },
                onContentsTheSame = { old, new -> old == new }
            )
    }
}
