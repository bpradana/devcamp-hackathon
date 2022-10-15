package com.tkpd.hackathon17.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Completion (
    val score: Float? = null,
    val emptyFields: ArrayList<String>? = null,
) : Parcelable