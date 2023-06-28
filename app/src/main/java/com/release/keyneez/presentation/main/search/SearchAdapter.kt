package com.release.keyneez.presentation.main.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.databinding.ItemSearchContentBinding
import com.release.keyneez.util.DiffCallback

class SearchAdapter(context: Context) :
    ListAdapter<ResponseGetSearchResultDto, RecyclerView.ViewHolder>(diffUtil) {
    var data = listOf<ResponseGetSearchResultDto>()
    private val inflater by lazy { LayoutInflater.from(context) }
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
        fun setSearch(search: ResponseGetSearchResultDto) {
            binding.data = search
        }
        // 여기 디테일뷰와 연결하는 코드 작성하기
    }

    companion object {
        private val diffUtil = DiffCallback<ResponseGetSearchResultDto>(
            onItemsTheSame = { old, new -> old.contentPk == new.contentPk },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
