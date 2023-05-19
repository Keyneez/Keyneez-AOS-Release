package com.release.keyneez.presentation.main.explore.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.release.keyneez.databinding.ItemExploreContentBinding
import com.release.keyneez.domain.model.Activity

class RecentAdapter : RecyclerView.Adapter<RecentAdapter.RecentViewHolder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val binding =
            ItemExploreContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
