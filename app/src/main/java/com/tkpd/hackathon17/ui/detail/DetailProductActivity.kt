package com.tkpd.hackathon17.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tkpd.hackathon17.R
import com.tkpd.hackathon17.data.model.Size
import com.tkpd.hackathon17.data.response.ProductResponse
import com.tkpd.hackathon17.databinding.ActivityDetailProductBinding
import com.tkpd.hackathon17.ui.input.InputProductActivity.Companion.TAG
import com.tkpd.hackathon17.viewmodel.ViewModelFactory

class DetailProductActivity : AppCompatActivity(){
    private lateinit var binding: ActivityDetailProductBinding
    private lateinit var viewModel: DetailProductViewModel
    private lateinit var product: ProductResponse
    private lateinit var sizesAdapter: DetailSizeAdapter
    private lateinit var listSizes: ArrayList<Size>

    companion object {
        const val EXTRA_PRODUCT = "extra product"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Product"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailProductViewModel::class.java]

        listSizes = java.util.ArrayList(0)
        val productId = intent.getStringExtra(EXTRA_PRODUCT)!!
        viewModel.getDetailProduct(productId).observe(this) {
            product = it
            Log.d(TAG, "product == $product")
            populateProduct()
        }
    }

    private fun populateProduct() {
        binding.apply {
            tvTitle.text = product.title
            tvPrice.text = StringBuilder("Rp. ${product.price}")
            tvMaterial.text = product.specification?.material
            tvType.text = product.specification?.type
            tvDesc.text = product.description
            tvScore.text = product.completion?.score?.toInt().toString()

            Glide.with(this@DetailProductActivity)
                .load(product.image)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imgImage)

            populateSizes()
            populateEmptyFields(product.completion?.emptyFields)
            populateTags(product.tags)
        }
    }

    private fun populateSizes() {
        product.specification?.sizes?.let { listSizes.addAll(it) }
        sizesAdapter = DetailSizeAdapter(listSizes)
        binding.apply {
            rvSizes.layoutManager = LinearLayoutManager(this@DetailProductActivity)
            rvSizes.setHasFixedSize(true)
            rvSizes.adapter = sizesAdapter
        }
    }

    private fun populateEmptyFields(emptyFields: ArrayList<String>?) {
        if (emptyFields != null) {
            val emptyFieldsAdapter = EmptyFieldsAdapter(emptyFields)
            binding.apply {
                rvEmptyFieldss.layoutManager = LinearLayoutManager(this@DetailProductActivity, LinearLayoutManager.HORIZONTAL, false)
                rvEmptyFieldss.setHasFixedSize(true)
                rvEmptyFieldss.adapter = emptyFieldsAdapter
            }
        } else {
            binding.pleaseComplete.visibility = View.GONE
        }
    }

    private fun populateTags(tags: ArrayList<String>?) {
        if (tags != null) {
            val tagsAdapter = DetailTagsAdapter(tags)
            binding.apply {
                rvTags.layoutManager = LinearLayoutManager(this@DetailProductActivity, LinearLayoutManager.HORIZONTAL, false)
                rvTags.setHasFixedSize(true)
                rvTags.adapter = tagsAdapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}