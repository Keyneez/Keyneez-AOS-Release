package com.release.keyneez.presentation.main.like

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.databinding.ItemLikeContentBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.util.DiffCallback
import com.release.keyneez.util.extension.setOnSingleClickListener

class LikeAdapter(
    private val setItemsSelected: (Int) -> List<Int>,
    private val getSelectedIdsCount: (Int) -> Int,
    private val isEdit: LiveData<Boolean>
) : ListAdapter<Activity, RecyclerView.ViewHolder>(diffUtil) {
    private var selectedActivity = arrayListOf<Activity>()

    inner class LikeViewHolder(
        private val binding: ItemLikeContentBinding,
        private val setItemsSelected: (Int) -> List<Int>,
        private val getSelectedIdsCount: (Int) -> Int,
        private val isEdit: LiveData<Boolean>
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Activity) {
            with(binding) {
                data = item
                ivLikeBackground.setOnSingleClickListener {
                    if (isEdit.value == true) {
                        item.isSelected = !item.isSelected
                        setItemsSelected(item.id)
                        binding.ivLikeCheckedBackground.visibility =
                            if (item.isSelected) View.VISIBLE else View.GONE
                        binding.btnLikeChecked.visibility =
                            if (item.isSelected) View.VISIBLE else View.GONE
                    }
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
            isEdit
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
