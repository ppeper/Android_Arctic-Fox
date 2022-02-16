package com.kyonggi.dependencyinjectionexample

import android.util.Log
import javax.inject.Inject

class SIMCard @Inject constructor (private val serviceProvider: ServiceProvider) {

    init {
        Log.i("TAG", "유심칩 생성완료!")
    }

    fun getConnection() {
        serviceProvider.getServiceProvider()
    }

}
