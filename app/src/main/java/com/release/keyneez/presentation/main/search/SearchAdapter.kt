package com.release.keyneez.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.databinding.ItemSearchContentBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.util.DiffCallback

class SearchAdapter : ListAdapter<ResponseGetSearchResultDto, RecyclerView.ViewHolder>(diffUtil) {
    var data = listOf<ResponseGetSearchResultDto>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchViewHolder(
            ItemSearchContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchViewHolder) holder.setSearch(getItem(position))
    }

    class SearchViewHolder(private val binding: ItemSearchContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setSearch(search: Activity) {
            binding.data = search
        }
        // 여기 코드 더 적기
        binding.root.setOnClickListener {
            // 디테일뷰와 연결하기
        }
    }

    companion object {
        private val diffUtil = DiffCallback<Activity>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
