package com.release.keyneez.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.release.keyneez.databinding.ItemSearchContentBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.util.DiffCallback

class SearchAdapter : ListAdapter<Activity, RecyclerView.ViewHolder>(diffUtil) {
    var data = listOf<Activity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchViewHolder(
            ItemSearchContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchViewHolder) holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = data.size
    class SearchViewHolder(private val binding: ItemSearchContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Activity) {
            with(binding) {
                ivSearchBackground.load(item.background)
                tvSearchCategory.text = item.category
                tvSearchContentTitle.text = item.title
                tvSearchDate.text = item.date
                // TODO : root.setOnSingleClickListener 구현
            }
        }
    }

//    override fun getItemCount(): Int = data.size

    companion object {
        private val diffUtil = DiffCallback<Activity>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
