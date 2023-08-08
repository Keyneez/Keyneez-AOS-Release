package com.release.keyneez.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.databinding.ItemSearchContentBinding
import com.release.keyneez.util.DiffCallback
import com.release.keyneez.util.extension.setOnSingleClickListener

class SearchAdapter(private val clickLike: (Int, Boolean) -> Unit) :
    ListAdapter<ResponseGetSearchResultDto, RecyclerView.ViewHolder>(diffUtil) {
    var data = listOf<ResponseGetSearchResultDto>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemSearchContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchAdapter.SearchViewHolder(
            binding,
            clickLike
        )
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchViewHolder) holder.setSearch(getItem(position))
    }

    class SearchViewHolder(
        private val binding: ItemSearchContentBinding,
        private val clickLike: (Int, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setSearch(search: ResponseGetSearchResultDto) {
            binding.data = search
            binding.btnSearchLiked.setOnSingleClickListener {
                clickLike(search.content, search.Likes.isNotEmpty())
                if (search.Likes.isNotEmpty()) {
                    search.Likes = listOf()
                    return@setOnSingleClickListener
                }
                search.Likes = listOf(ResponseGetSearchResultDto.Liked(0, 0, 0))
            }
            // 여기 상세뷰와 연결하는 코드 작성하기
        }
    }

    companion object {
        private val diffUtil =
            DiffCallback<ResponseGetSearchResultDto>(
                onItemsTheSame = { old, new -> old.content == new.content },
                onContentsTheSame = { old, new -> old == new }
            )
    }
}
