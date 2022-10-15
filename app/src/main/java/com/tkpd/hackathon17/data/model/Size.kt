package com.tkpd.hackathon17.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Size (
    val name: String? = null,
    val waist: Float? = null,
    val chest: Float? = null,
    val neck: Float? = null,
) : Parcelable