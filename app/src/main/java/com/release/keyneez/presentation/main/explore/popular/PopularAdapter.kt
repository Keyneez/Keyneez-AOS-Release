package com.release.keyneez.presentation.main.explore.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.data.entity.response.ResponseGetPopularDto
import com.release.keyneez.databinding.ItemPopularContentBinding
import com.release.keyneez.util.DiffCallback

class PopularAdapter : ListAdapter<ResponseGetPopularDto, RecyclerView.ViewHolder>(diffUtil) {
    var data = listOf<ResponseGetPopularDto>()

    class PopularViewHolder(private val binding: ItemPopularContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setPopularContent(popular: ResponseGetPopularDto) {
            binding.data = popular
            // 여기도 누르면 상세뷰로 가는 코드짜기
        }
    }

    override fun getItemCount(): Int = data.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PopularViewHolder(
            ItemPopularContentBinding.inflate(
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
        private val diffUtil = DiffCallback<ResponseGetPopularDto>(
            onItemsTheSame = { old, new -> old.content == new.content },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
