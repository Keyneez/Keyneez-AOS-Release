package com.release.keyneez.presentation.main.explore.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.release.keyneez.databinding.ItemExploreContentBinding
import com.release.keyneez.domain.model.Activity

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    var data = listOf<Activity>()

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
