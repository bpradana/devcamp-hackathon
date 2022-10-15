package com.tkpd.hackathon17.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkpd.hackathon17.data.response.ProductResponse
import com.tkpd.hackathon17.databinding.ActivityListProductBinding
import com.tkpd.hackathon17.viewmodel.ViewModelFactory

class ListProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListProductBinding
    private lateinit var viewModel: ListProductViewModel
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ListProductViewModel::class.java]

        populateProducts()
    }

    private fun populateProducts() {
        viewModel.getListProduct().observe(this) { listProducts ->
            adapter = ProductAdapter(listProducts)
            binding.apply {
                rvProducts.layoutManager = LinearLayoutManager(this@ListProductActivity)
                rvProducts.setHasFixedSize(true)
                rvProducts.adapter = adapter
            }

            adapter.setOnItemClickCallback(object : ProductAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ProductResponse) {
                    TODO("Not yet implemented")
                }

                override fun onItemDelete(data: ProductResponse) {
                    TODO("Not yet implemented")
                }
            })
        }

    }
}