package com.tkpd.hackathon17.data.api

import com.tkpd.hackathon17.data.model.Product
import com.tkpd.hackathon17.data.response.ProductResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiServices {
    @GET("product")
    fun getListProducts(): Call<ArrayList<ProductResponse>>

    @Headers("Content-Type: application/json")
    @POST("product")
    fun addProduct(@Body product: Product): Call<Product>

    @GET("product/{id}")
    fun getDetailProduct(
        @Path("id") id: String
    ) : Call<ProductResponse>

    @PUT("product")
    fun updateProduct(@Body product: Product?): Call<Product>
}