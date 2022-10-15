package com.tkpd.hackathon17.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tkpd.hackathon17.data.DataRepository
import com.tkpd.hackathon17.data.response.ProductResponse

class ListProductViewModel(
    private val repository: DataRepository
) : ViewModel() {
    fun getListProduct(): LiveData<ArrayList<ProductResponse>> = repository.getListProducts()
}