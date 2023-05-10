package com.release.keyneez.presentation.main.explore.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.release.keyneez.data.entity.ExploreData
import com.release.keyneez.databinding.ItemExploreContentBinding

class RecentAdapter : RecyclerView.Adapter<RecentAdapter.InfoViewHolder>() {
    var data = listOf<ExploreData>()
    class InfoViewHolder(private val binding: ItemExploreContentBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding = ItemExploreContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
