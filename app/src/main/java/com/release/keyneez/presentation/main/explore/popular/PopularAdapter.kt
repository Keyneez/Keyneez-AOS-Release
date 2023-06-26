package com.release.keyneez.presentation.main.explore.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.release.keyneez.databinding.ItemExploreContentBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.presentation.main.like.LikeFragment
import com.release.keyneez.util.DiffCallback

class PopularAdapter : ListAdapter<Activity, RecyclerView.ViewHolder>(diffUtil) {
    lateinit var likeList: ArrayList<Activity>
    private lateinit var likeFragment: LikeFragment

    class PopularViewHolder(private val binding: ItemExploreContentBinding) :
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
        return PopularViewHolder(
            ItemExploreContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PopularViewHolder) holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = DiffCallback<Activity>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
