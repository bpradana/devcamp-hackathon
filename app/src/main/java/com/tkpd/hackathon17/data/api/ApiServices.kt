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

//    @GET("search.json")
//    fun getListReverseImages(
//        @Query("engine") engine: String,
//        @Query("api_key") apiKey: String,
//        @Query("image_url") imageUrl: String
//    ): Call<ArrayList<ImageResults>>

}