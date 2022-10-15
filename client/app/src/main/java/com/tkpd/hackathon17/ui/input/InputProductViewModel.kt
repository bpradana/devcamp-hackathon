package com.tkpd.hackathon17.ui.input

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.tkpd.hackathon17.data.DataRepository
import com.tkpd.hackathon17.data.model.Product

class InputProductViewModel (
    private val repository: DataRepository
) : ViewModel() {
    fun addProductToStorage(product: Product, imageUri: Uri) = repository.addProductToStorage(product, imageUri)
}