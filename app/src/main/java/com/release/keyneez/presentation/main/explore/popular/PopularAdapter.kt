package com.release.keyneez.presentation.main.explore.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.release.keyneez.domain.model.ExploreData
import com.release.keyneez.databinding.ItemExploreContentBinding

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    var data = listOf<ExploreData>()

    class PopularViewHolder(private val binding: ItemExploreContentBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding =
            ItemExploreContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
