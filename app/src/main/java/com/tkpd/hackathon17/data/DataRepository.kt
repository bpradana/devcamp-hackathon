package com.tkpd.hackathon17.data

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.tkpd.hackathon17.data.model.Product

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
                    product.desc,
                    product.tags,
                    firebaseImageUri.toString(),
                    product.exif
                )
            }
            Log.d(TAG, "successfully upload image $uploadImage")
        }.addOnFailureListener {
            Log.d(TAG, "failed upload image $it")
        }
        return true
    }

}