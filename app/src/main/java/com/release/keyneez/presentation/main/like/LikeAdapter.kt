package com.release.keyneez.presentation.main.like

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.R
import com.release.keyneez.databinding.ItemLikeContentBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.util.extension.setOnSingleClickListener

class LikeAdapter : ListAdapter<Activity, LikeAdapter.LikeViewHolder>(DiffUtils()) {
    //    private val binding: ItemLikeContentBinding,
//    private val setItemsSelected: (Int) -> List<Int>,
//    private val getSelectedIdsCount: (Int) -> Int
    private var selectedActivity = arrayListOf<Activity>()
    var item_list: ArrayList<Activity>? = null

    inner class LikeViewHolder(
        private val binding: ItemLikeContentBinding
//        private val setItemsSelected: (Int) -> List<Int>,
//        private val getSelectedIdsCount: (Int) -> Int
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Activity) {
            with(binding) {
                tvLikeCategory.text = item.category
                tvLikeContentTitle.text = item.title
                tvLikeDate.text = item.date
                binding.root.setOnClickListener {
                    applySelection(binding, item)
                    onItemClickListener?.let { it(item) }
                }
                // 이게 맞을까..?
                ivLikeBackground.setOnSingleClickListener {
                    // isSelcted 여부를 반대로
                    item.isSelected = !item.isSelected
                    // id 만 넘겨주는 함수 호출
                    applySelection(binding, item)
                }
            }
        }
    }

    fun ModelAdapter(arrayList: ArrayList<Activity>) {
        item_list = arrayList
    }

    private fun applySelection(binding: ItemLikeContentBinding, expense: Activity) {
        if (selectedActivity.contains(expense)) {
            selectedActivity.remove(expense)
            notifyDataSetChanged()
            changeBackground(binding, R.color.gray050)
        } else {
            selectedActivity.add(expense)
            changeBackground(binding, R.color.gray900)
        }
    }

    private fun changeBackground(binding: ItemLikeContentBinding, resId: Int) {
        binding.layoutLikeRv.setBackgroundColor(ContextCompat.getColor(binding.root.context, resId))
    }

    fun getSelectedExpense() = selectedActivity.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LikeViewHolder(
        ItemLikeContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
//        return LikeViewHolder(binding)
//            binding,
//            setItemsSelected,
//            getSelectedIdsCount
//        )

    override fun onBindViewHolder(holder: LikeAdapter.LikeViewHolder, position: Int) {
//        if (holder is LikeAdapter.LikeViewHolder) holder.bind(getItem(position))
        holder.bind(getItem(position))
    }

    //    companion object {
//        private val diffUtil =
//            DiffCallback<Activity>(
//                onItemsTheSame = { old, new -> old.id == new.id },
//                onContentsTheSame = { old, new -> old == new }
//            )
//    }
    private class DiffUtils : DiffUtil.ItemCallback<Activity>() {
        override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem == newItem
        }
    }

    private var onItemClickListener: ((Activity) -> Unit)? = null

    fun setOnItemClickListener(listener: (Activity) -> Unit) {
        onItemClickListener = listener
    }
}
