package com.tkpd.hackathon17.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tkpd.hackathon17.R
import com.tkpd.hackathon17.data.response.ProductResponse
import com.tkpd.hackathon17.databinding.ItemProductBinding

class ProductAdapter(private val listItem: ArrayList<ProductResponse>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var onItemClickDelete: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: ProductResponse)
        fun onItemDelete(data: ProductResponse)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
        this.onItemClickDelete = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listItem[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listItem.size

    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductResponse) {
            with(binding) {
                itemTitle.text = item.title
                itemPrice.text = StringBuilder("Rp. ${item.price}")
                tvScore.text = StringBuilder("${item.completion?.score?.toInt()}")
                Glide.with(itemView.context)
                    .load(item.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(itemImage)
            }
        }
    }
}