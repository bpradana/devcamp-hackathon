package com.tkpd.hackathon17.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.hackathon17.data.model.Size
import com.tkpd.hackathon17.databinding.ItemDetailSizeBinding

class DetailSizeAdapter(private val listItem: ArrayList<Size>) : RecyclerView.Adapter<DetailSizeAdapter.SizesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizesViewHolder {
        val binding = ItemDetailSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizesViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listItem.size

    inner class SizesViewHolder(private val binding: ItemDetailSizeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Size) {
            with(binding) {
                tvSizeName.text = "${item.name} (waist ${item.waist}, chest ${item.chest}, neck ${item.neck}) cm"
            }
        }
    }
}