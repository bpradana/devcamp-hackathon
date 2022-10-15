package com.tkpd.hackathon17.ui.input

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.hackathon17.data.model.Size
import com.tkpd.hackathon17.databinding.ItemSizeBinding

class SizesAdapter(private val listItem: ArrayList<Size>) : RecyclerView.Adapter<SizesAdapter.SizesViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var onItemClickDelete: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Size)
        fun onItemDelete(data: Size)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
        this.onItemClickDelete = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizesViewHolder {
        val binding = ItemSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizesViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listItem[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listItem.size

    inner class SizesViewHolder(private val binding: ItemSizeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Size) {
            with(binding) {
                tvSizeName.text = "${item.name} ( ${item.waist} x ${item.chest} x ${item.neck} )"
                btnDelete.setOnClickListener { onItemClickCallback.onItemDelete(item) }
            }
        }
    }
}