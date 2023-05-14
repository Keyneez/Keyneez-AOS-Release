package com.release.keyneez.presentation.main.explore.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.release.keyneez.databinding.ItemExploreContentBinding
import com.release.keyneez.domain.model.ExploreData

class RecentAdapter : RecyclerView.Adapter<RecentAdapter.RecentViewHolder>() {
    var data = listOf<ExploreData>()

    class RecentViewHolder(private val binding: ItemExploreContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExploreData) {
            binding.ivExploreBackground.load(item.background)
            binding.tvExploreCategory.text = item.category
            binding.tvExploreContentTitle.text = item.title
            binding.tvExploreDate.text = item.date
            binding.root.setOnClickListener {
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
