package com.tkpd.hackathon17.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Specification (
    val type: String? = null,
    val material: String? = null,
    val sizes: ArrayList<Size>? = null,
) : Parcelable