package com.kyonggi.dependencyinjectionexample

import android.util.Log

class Battery {

    init {
        Log.i("TAG", "배터리 생성완료!")
    }

    fun getPower() {
        Log.i("TAG", "배터리 전원공급!")
    }
}
