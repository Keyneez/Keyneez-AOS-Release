package com.release.keyneez.presentation.main.explore.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.release.keyneez.databinding.ItemExploreContentBinding
import com.release.keyneez.domain.model.Activity

class RecentAdapter : ListAdapter<Activity, RecyclerView.ViewHolder>(ActivityDiffCallback) {
    var data = listOf<Activity>()

    class RecentViewHolder(private val binding: ItemExploreContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Activity) {
            with(binding) {
                ivExploreBackground.load(item.background)
                tvExploreCategory.text = item.category
                tvExploreContentTitle.text = item.title
                tvExploreDate.text = item.date
                // TODO : root.setOnSingleClickListener 구현
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecentViewHolder(
            ItemExploreContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = super.getItemCount()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecentViewHolder) holder.bind(getItem(position))
        val item = getItem(position)
    }

    object ActivityDiffCallback : DiffUtil.ItemCallback<Activity>() {
        override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
