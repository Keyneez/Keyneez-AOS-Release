package com.release.keyneez.presentation.main.explore.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.data.entity.response.ResponseGetPopularDto
import com.release.keyneez.databinding.ItemPopularContentBinding
import com.release.keyneez.util.DiffCallback
import com.release.keyneez.util.extension.setOnSingleClickListener

class PopularAdapter(
    private val clickLike: (Int, Boolean) -> Unit
) : ListAdapter<ResponseGetPopularDto, RecyclerView.ViewHolder>(diffUtil) {
    var data = listOf<ResponseGetPopularDto>()

    class PopularViewHolder(
        private val binding: ItemPopularContentBinding,
        private val clickLike: (Int, Boolean) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun setPopularContent(popular: ResponseGetPopularDto) {
            binding.data = popular
            binding.btnPopularLiked.setOnSingleClickListener {
                clickLike(popular.content, popular.Likes.isNotEmpty())
                if (popular.Likes.isNotEmpty()) {
                    popular.Likes = listOf()
                    // TODO : 객체 없애는 로직
                    return@setOnSingleClickListener
                }
                popular.Likes = listOf(ResponseGetPopularDto.Liked(0, 0, 0))
                // TODO: 객체 집어 넣어주는 로직
            }
            // 여기도 누르면 상세뷰로 가는 코드짜기
        }
    }

    override fun getItemCount(): Int = data.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPopularContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PopularViewHolder(
            binding,
            clickLike
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PopularViewHolder) holder.setPopularContent(getItem(position))
    }

    companion object {
        private val diffUtil = DiffCallback<ResponseGetPopularDto>(
            onItemsTheSame = { old, new -> old.content == new.content },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
