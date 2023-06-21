package com.release.keyneez.presentation.main.like

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.release.keyneez.databinding.ItemLikeContentBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.util.DiffCallback

class LikeAdapter : ListAdapter<Activity, RecyclerView.ViewHolder>(diffUtil) {

    lateinit var likeList: List<Activity>

    class LikeViewHolder(private val binding: ItemLikeContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Activity) {
            with(binding) {
                ivLikeBackground.load(item.background)
                tvLikeCategory.text = item.category
                tvLikeContentTitle.text = item.title
                tvLikeDate.text = item.date
                // TODO : root.setOnSingleClickListener 구현
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LikeViewHolder(
            ItemLikeContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
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
}
