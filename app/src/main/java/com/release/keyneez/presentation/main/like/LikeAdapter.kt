package com.release.keyneez.presentation.main.like

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.databinding.ItemLikeContentBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.util.DiffCallback
import com.release.keyneez.util.extension.setOnSingleClickListener

class LikeAdapter(
    private val setItemsSelected: (Int) -> List<Int>,
    private val isEdit: LiveData<Boolean>,
    private val clearSelectedItems: () -> Unit
) : ListAdapter<ResponseGetLikeDto, RecyclerView.ViewHolder>(diffUtil) {
    var data = listOf<ResponseGetLikeDto>()

    inner class LikeViewHolder(
        private val binding: ItemLikeContentBinding,
        private val setItemsSelected: (Int) -> List<Int>,
        private val isEdit: LiveData<Boolean>,
        private val clearSelectedItems: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setLikeContent(item: ResponseGetLikeDto) {
            with(binding) {
                ivLikeBackground.setOnSingleClickListener {
                    if (isEdit.value == true) {
                        item.isSelected = !item.isSelected
                        setItemsSelected(item.content)
                        binding.ivLikeCheckedBackground.visibility =
                            if (item.isSelected) View.VISIBLE else View.GONE
                        binding.btnLikeChecked.visibility =
                            if (item.isSelected) View.VISIBLE else View.GONE
                    }
                }
            }
//            binding.root.setOnClickListener {
//                val intent = Intent(binding.root.context, DetailActivity::class.java)
//                intent.putExtra("contentId", item.content)
//                ContextCompat.startActivity(binding.root.context, intent, null)
//            }
            isEdit.observeForever { isEdit ->
                if (isEdit == false) {
                    item.isSelected = false
                    binding.ivLikeCheckedBackground.visibility = View.GONE
                    binding.btnLikeChecked.visibility = View.GONE
                    clearSelectedItems()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLikeContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LikeViewHolder(
            binding,
            setItemsSelected,
            isEdit,
            clearSelectedItems
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LikeViewHolder) holder.setLikeContent(getItem(position))
    }

    private var onItemClickListener: ((Activity) -> Unit)? = null

    companion object {
        private val diffUtil =
            DiffCallback<ResponseGetLikeDto>(
                onItemsTheSame = { old, new -> old.content == new.content },
                onContentsTheSame = { old, new -> old == new }
            )
    }
}
