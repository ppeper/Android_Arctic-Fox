package com.kyonggi.dependencyinjectionexample

import android.util.Log
import javax.inject.Inject

class Battery @Inject constructor() {

    init {
        Log.i("TAG", "배터리 생성완료!")
    }

    fun getPower() {
        Log.i("TAG", "배터리 전원공급!")
    }
}
