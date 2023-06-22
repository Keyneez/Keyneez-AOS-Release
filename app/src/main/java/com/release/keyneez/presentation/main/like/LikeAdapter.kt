package com.release.keyneez.presentation.main.like

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.databinding.ItemLikeContentBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.util.DiffCallback
import com.release.keyneez.util.extension.setOnSingleClickListener

// 선택한 것 선택 취소한 것 인자로, 뷰홀더 인자에도!
class LikeAdapter(private val setItemsSelected: (Int) -> Int) :
    ListAdapter<Activity, RecyclerView.ViewHolder>(diffUtil) {
    lateinit var likeList: List<Activity>

    class LikeViewHolder(
        private val binding: ItemLikeContentBinding,
        private val setItemsSelected: (Int) -> Int
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Activity) {
            with(binding) {
                data = item
                // 이게 맞을까..?
                ivLikeBackground.setOnSingleClickListener {
                    // isSelcted 여부를 반대로
                    item.isSelected = !item.isSelected
                    // id 만 넘겨주는 함수 호출
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
            setItemsSelected
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

    // 기존에 adpater가 가지고 있는 데이터를 가져 와서 변경 가능한 데이터로 바꿈
    fun removeItem(position: Int) {
        val newList = likeList.toMutableList()
        newList.removeAt(position)
        submitList(newList)
    }
}
