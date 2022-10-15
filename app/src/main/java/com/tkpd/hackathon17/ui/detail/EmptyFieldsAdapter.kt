package com.tkpd.hackathon17.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.hackathon17.databinding.ItemEmptyFieldsBinding

class EmptyFieldsAdapter(private val listItem: ArrayList<String>) : RecyclerView.Adapter<EmptyFieldsAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemEmptyFieldsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listItem.size

    inner class ItemViewHolder(private val binding: ItemEmptyFieldsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(binding) {
                tvField.text = item
            }
        }
    }
}