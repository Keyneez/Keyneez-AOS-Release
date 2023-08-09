package com.release.keyneez.presentation.main.explore.recent

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.data.entity.response.ResponseGetRecentDto
import com.release.keyneez.databinding.ItemRecentContentBinding
import com.release.keyneez.util.DiffCallback
import com.release.keyneez.util.extension.setOnSingleClickListener

class RecentAdapter(private val clickLike: (Int, Boolean) -> Unit) :
    ListAdapter<ResponseGetRecentDto, RecyclerView.ViewHolder>(diffUtil) {
    var data = listOf<ResponseGetRecentDto>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRecentContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecentAdapter.RecentViewHolder(
            binding,
            clickLike
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecentViewHolder) holder.setRecentContent(getItem(position))
    }

    class RecentViewHolder(
        private val binding: ItemRecentContentBinding,
        private val clickLike: (Int, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setRecentContent(recent: ResponseGetRecentDto) {
            binding.data = recent
            binding.btnRecentLiked.setOnSingleClickListener {
                clickLike(recent.content, recent.Likes.isNotEmpty())
                if (recent.Likes.isNotEmpty()) {
                    recent.Likes = listOf()
                    return@setOnSingleClickListener
                }
                recent.Likes = listOf(ResponseGetRecentDto.Liked(0, 0, 0))
            }
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("contentId", recent.content)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    companion object {
        private val diffUtil =
            DiffCallback<ResponseGetRecentDto>(
                onItemsTheSame = { old, new -> old.content == new.content },
                onContentsTheSame = { old, new -> old == new }
            )
    }
}
