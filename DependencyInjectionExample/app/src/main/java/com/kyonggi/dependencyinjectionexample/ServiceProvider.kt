package com.kyonggi.dependencyinjectionexample

import android.util.Log

class ServiceProvider {

    init {
        Log.i("TAG", "서비스 제공자 생성완료!")
    }

    fun getServiceProvider() {
        Log.i("TAG", "유심칩 연결완료!")
    }

}
