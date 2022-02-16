package com.kyonggi.dependencyinjectionexample

import android.util.Log
import javax.inject.Inject

class SmartPhone @Inject constructor(val battery: Battery, val simCard: SIMCard, val memoryCard: MemoryCard) {

    init {
        battery.getPower()
        simCard.getConnection()
        memoryCard.getMemorySpaceAvailability()
        Log.i("TAG", "스마트폰 생성완료")
    }

    fun makeCall() {
        Log.i("TAG", "전화중...")
    }
}