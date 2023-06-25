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
class LikeAdapter(
    private val setItemsSelected: (Int) -> List<Int>,
    private val getSelectedIdsCount: (Int) -> Int,
    private val deleteItems: (List<Int>) -> Unit
) : ListAdapter<Activity, RecyclerView.ViewHolder>(diffUtil) {
    lateinit var likeList: List<Activity>

    class LikeViewHolder(
        private val binding: ItemLikeContentBinding,
        private val setItemsSelected: (Int) -> List<Int>,
        private val getSelectedIdsCount: (Int) -> Int,
        private val deleteItems: (List<Int>) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
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
        val binding = ItemLikeContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LikeViewHolder(
            binding,
            setItemsSelected,
            getSelectedIdsCount,
            deleteItems
        )
    }

//    fun replaceItems(items: List<Activity>) {
//        submitList(items)
//    }

    fun deleteItems(selectedIds: List<Int>) {
        val updatedDataList = likeList.toMutableList()
        val removedItems = mutableListOf<Activity>()
        // removedItems는 삭제할 아이템들을 모아두는 곳이다.
        for (item in updatedDataList) {
            if (item.id in selectedIds) {
                removedItems.add(item)
            }
        }
        updatedDataList.removeAll(removedItems)

        likeList = updatedDataList.toList()

        // Notify the adapter about the removed items
        for (item in removedItems) {
            val position = likeList.indexOf(item)
            notifyItemRemoved(position)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LikeViewHolder) holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil =
            DiffCallback<Activity>(
                onItemsTheSame = { old, new -> old.id == new.id },
                onContentsTheSame = { old, new -> old == new }
            )
    }
}
