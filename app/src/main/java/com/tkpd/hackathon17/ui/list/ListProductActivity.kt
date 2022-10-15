package com.tkpd.hackathon17.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkpd.hackathon17.data.response.ProductResponse
import com.tkpd.hackathon17.databinding.ActivityListProductBinding
import com.tkpd.hackathon17.ui.detail.DetailProductActivity
import com.tkpd.hackathon17.ui.detail.DetailProductViewModel
import com.tkpd.hackathon17.ui.input.InputProductActivity
import com.tkpd.hackathon17.viewmodel.ViewModelFactory

class ListProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListProductBinding
    private lateinit var viewModel: ListProductViewModel
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "List of Products"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ListProductViewModel::class.java]

        populateProducts()
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, InputProductActivity::class.java))
        }
        binding.btnRefresh.setOnClickListener { populateProducts() }
        binding.btnReload.setOnClickListener { populateProducts() }
    }

    private fun populateProducts() {
        viewModel.getListProduct().observe(this) { listProducts ->
            if (listProducts.isNullOrEmpty()) {
                binding.btnRefresh.visibility = View.VISIBLE
                binding.btnReload.visibility = View.GONE
            } else {
                binding.btnRefresh.visibility = View.GONE
                binding.btnReload.visibility = View.VISIBLE
            }

            adapter = ProductAdapter(listProducts)
            binding.apply {
                rvProducts.layoutManager = LinearLayoutManager(this@ListProductActivity)
                rvProducts.setHasFixedSize(true)
                rvProducts.adapter = adapter
            }

            adapter.setOnItemClickCallback(object : ProductAdapter.OnItemClickCallback {
                override fun onItemDelete(data: ProductResponse) { }
                override fun onItemClicked(data: ProductResponse) {
                    Intent(this@ListProductActivity, DetailProductActivity::class.java).also {
                        it.putExtra(DetailProductActivity.EXTRA_PRODUCT, data.id)
                        startActivity(it)
                    }
                }

            })
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}