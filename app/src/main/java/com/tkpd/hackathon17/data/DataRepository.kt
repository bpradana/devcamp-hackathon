package com.tkpd.hackathon17.data

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

}