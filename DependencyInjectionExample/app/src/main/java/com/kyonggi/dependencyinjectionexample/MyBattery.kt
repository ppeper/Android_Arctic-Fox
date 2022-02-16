package com.kyonggi.dependencyinjectionexample

import android.util.Log
import javax.inject.Inject

class MyBattery @Inject constructor(): Battery {
    override fun getPower() {
        Log.i("TAG", "My배터리 전원공급!")
    }
}