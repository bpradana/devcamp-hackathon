package com.tkpd.hackathon17.data

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.storage.FirebaseStorage
import com.tkpd.hackathon17.data.api.ApiClient
import com.tkpd.hackathon17.data.model.Product
import com.tkpd.hackathon17.data.response.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository {
    companion object {
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository().apply {
                    instance = this
                }
            }
        private const val TAG = "REPOSITORY"
    }

    fun getListProducts(): LiveData<ArrayList<ProductResponse>> {
        val results = MutableLiveData<ArrayList<ProductResponse>>()
        ApiClient.create().getListProducts().enqueue(object : Callback<ArrayList<ProductResponse>> {
            override fun onFailure(call: Call<ArrayList<ProductResponse>>, t: Throwable) {
                t.message?.let { Log.d(TAG, it) }
            }
            override fun onResponse(call: Call<ArrayList<ProductResponse>>, response: Response<ArrayList<ProductResponse>>) {
                if (response.isSuccessful) {
                    results.postValue(response.body())
                    Log.d(TAG, response.body().toString())
                }
            }
        })
        return results
    }

    private fun addProduct(product: Product, onResult: (Product?) -> Unit) {
        ApiClient.create().addProduct(product).enqueue(object : Callback<Product> {
            override fun onFailure(call: Call<Product>, t: Throwable) {
                onResult(null)
            }
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                val addedProduct = response.body()
                onResult(addedProduct)
                Log.d(TAG, "product response $addedProduct")
            }
        }
        )
    }

    fun addProductToStorage(product: Product, imageUri: Uri): Boolean {
        val fileName = StringBuilder("${product.id}.jpg")
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")
        storageReference.putFile(imageUri).addOnSuccessListener { uploadImage ->
            storageReference.downloadUrl.addOnSuccessListener { firebaseImageUri ->
                Log.d(TAG, "image firebase uri $firebaseImageUri")
                val newProduct = Product(
                    product.id,
                    product.title,
                    product.price,
                    product.description,
                    product.tags,
                    firebaseImageUri.toString(),
                    product.exif,
                    product.specification
                )
                addProduct(newProduct) {
                    if (it != null) {
                        Log.d(TAG, "add product success $it")
                    } else {
                        Log.d(TAG, "add product failed $it")
                    }
                }
            }
            Log.d(TAG, "successfully upload image $uploadImage")
        }.addOnFailureListener {
            Log.d(TAG, "failed upload image $it")
        }
        return true
    }

}