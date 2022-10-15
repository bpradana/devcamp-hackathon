package com.tkpd.hackathon17.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.hackathon17.databinding.ItemDetailTagBinding

class DetailTagsAdapter(private val listItem: ArrayList<String>) : RecyclerView.Adapter<DetailTagsAdapter.TagsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val binding = ItemDetailTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listItem.size

    inner class TagsViewHolder(private val binding: ItemDetailTagBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(binding) {
                tvTag.text = "#$item"
            }
        }
    }
}