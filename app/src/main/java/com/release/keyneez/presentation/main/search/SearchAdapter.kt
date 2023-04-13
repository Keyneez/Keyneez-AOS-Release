package com.release.keyneez.presentation.main.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.data.model.response.ResponseGetSearchDto
import com.release.keyneez.databinding.ItemSearchContentBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.getViewHolder>()  {

    var data = listOf<ResponseGetSearchDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): getViewHolder {
        val binding =
            ItemSearchContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return getViewHolder(binding)
    }

    class getViewHolder(private val binding: ItemSearchContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseGetSearchDto) {
            binding.data = item
            binding.root.setOnClickListener {
//                val intent = Intent(binding.root.context, DetailActivity::class.java)  홈 상세페이지로 이동
//                intent.putExtra("contentId", item.key)
//                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }
}