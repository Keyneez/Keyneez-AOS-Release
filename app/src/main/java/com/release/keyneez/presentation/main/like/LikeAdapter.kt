package com.release.keyneez.presentation.main.like

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.release.keyneez.databinding.ItemLikeContentBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.util.DiffCallback

class LikeAdapter(
    private val setItemsCheckedBoxSelected: (Int) -> Boolean,
    private val ItemOnClick: (Int, Boolean) -> Unit,
    private val setDeletedItemsCount: (Int) -> Int
) : ListAdapter<Activity, RecyclerView.ViewHolder>(diffUtil) {

    lateinit var likeList: List<Activity>

    class LikeViewHolder(
        private val binding: ItemLikeContentBinding,
        private val setItemsCheckedBoxSelected: (Int) -> Boolean,
        private val ItemOnClick: (Int, Boolean) -> Unit,
        private val setDeletedItemsCount: (Int) -> Int
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Activity) {
            with(binding) {
                ivLikeBackground.load(item.background)
                tvLikeCategory.text = item.category
                tvLikeContentTitle.text = item.title
                tvLikeDate.text = item.date
                // TODO : root.setOnSingleClickListener 구현
            }
            binding.data = item
            with(binding) {
                // 이게 맞을까..?
                ivLikeBackground.isSelected = setItemsCheckedBoxSelected(absoluteAdapterPosition)
                ivLikeBackground.setOnClickListener {
                    ItemOnClick(absoluteAdapterPosition, ivLikeBackground.isSelected)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemLikeContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return LikeViewHolder(
            binding,
            setItemsCheckedBoxSelected,
            ItemOnClick,
            setDeletedItemsCount
        )
    }

    fun replaceItems(items: List<Activity>) {
        submitList(items)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LikeViewHolder) holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = DiffCallback<Activity>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }

    fun removeItem(position: Int) {
        val newList = likeList.toMutableList() // 기존에 adpater가 가지고 있는 데이터를 가져 와서 변경 가능한 데이터로 바꿈
        newList.removeAt(position)
        submitList(newList)
    }
}
