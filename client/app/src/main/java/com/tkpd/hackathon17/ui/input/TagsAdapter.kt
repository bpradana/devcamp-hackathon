package com.tkpd.hackathon17.ui.input

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.hackathon17.databinding.ItemTagBinding

class TagsAdapter(private val listItem: ArrayList<String>) : RecyclerView.Adapter<TagsAdapter.TagsViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var onItemClickDelete: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: String)
        fun onItemDelete(data: String)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
        this.onItemClickDelete = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val binding = ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listItem[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listItem.size

    inner class TagsViewHolder(private val binding: ItemTagBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(binding) {
                tvTagName.text = item
                btnDelete.setOnClickListener { onItemClickCallback.onItemDelete(item) }
            }
        }
    }
}