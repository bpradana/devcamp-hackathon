package com.tkpd.hackathon17.di

import android.content.Context
import com.tkpd.hackathon17.data.DataRepository

object Injection {
    fun provideRepository(context: Context): DataRepository {
        return DataRepository.getInstance()
    }
}