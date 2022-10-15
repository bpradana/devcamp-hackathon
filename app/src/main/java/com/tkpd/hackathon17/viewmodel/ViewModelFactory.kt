package com.tkpd.hackathon17.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.hackathon17.data.DataRepository
import com.tkpd.hackathon17.di.Injection
import com.tkpd.hackathon17.ui.input.InputProductViewModel
import com.tkpd.hackathon17.ui.list.ListProductViewModel

class ViewModelFactory private constructor(private val dataRepository: DataRepository)
    : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(InputProductViewModel::class.java) -> {
                InputProductViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(ListProductViewModel::class.java) -> {
                ListProductViewModel(dataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}