package com.release.keyneez.presentation.main.explore.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.data.entity.response.ResponseGetContentDto
import com.release.keyneez.databinding.ItemExploreContentBinding
import com.release.keyneez.util.DiffCallback

class PopularAdapter : ListAdapter<ResponseGetContentDto, RecyclerView.ViewHolder>(diffUtil) {
    var data = listOf<ResponseGetContentDto>()

    class PopularViewHolder(private val binding: ItemExploreContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setPopularContent(popular: ResponseGetContentDto) {
            binding.data = popular
            // 여기도 누르면 상세뷰로 가는 코드짜기
        }
    }

    override fun getItemCount(): Int = data.size
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
        if (holder is PopularViewHolder) holder.setPopularContent(getItem(position))
    }

    companion object {
        private val diffUtil = DiffCallback<ResponseGetContentDto>(
            onItemsTheSame = { old, new -> old.contentPk == new.contentPk },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
