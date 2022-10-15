package com.tkpd.hackathon17.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    val id: String? = null,
    val title: String? = null,
    val price: Float? = null,
    val description: String? = null,
    val tags: List<String>? = null,
    val image: String? = null,
    val exif: Boolean = false,
    val specification: Specification? = null,
) : Parcelable