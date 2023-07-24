package com.release.keyneez.presentation.main.explore.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.data.entity.response.ResponseGetRecentDto
import com.release.keyneez.databinding.ItemRecentContentBinding
import com.release.keyneez.util.DiffCallback

class RecentAdapter : ListAdapter<ResponseGetRecentDto, RecyclerView.ViewHolder>(diffUtil) {
    var data = listOf<ResponseGetRecentDto>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecentViewHolder(
            ItemRecentContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecentViewHolder) holder.setRecentContent(getItem(position))
    }

    class RecentViewHolder(private val binding: ItemRecentContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setRecentContent(recent: ResponseGetRecentDto) {
            binding.data = recent
            // 여기도 누르면 상세뷰로 가는 코드짜기
        }
    }

    companion object {
        private val diffUtil = DiffCallback<ResponseGetRecentDto>(
            onItemsTheSame = { old, new -> old.content == new.content },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
