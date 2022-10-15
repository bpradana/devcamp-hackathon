package com.tkpd.hackathon17.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tkpd.hackathon17.data.DataRepository
import com.tkpd.hackathon17.data.response.ProductResponse

class DetailProductViewModel (
    private val repository: DataRepository
) : ViewModel() {
    fun getDetailProduct(productId: String): LiveData<ProductResponse> = repository.getDetailProduct(productId)
}