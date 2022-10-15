package com.tkpd.hackathon17.data.response

import com.google.gson.annotations.SerializedName
import com.tkpd.hackathon17.data.model.Completion
import com.tkpd.hackathon17.data.model.Specification

data class ProductResponse(

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("price")
    val price: Float? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("tags")
    val tags: ArrayList<String>? = null,

    @field:SerializedName("specification")
    val specification: Specification? = null,

    @field:SerializedName("completion")
    val completion: Completion? = null,
)