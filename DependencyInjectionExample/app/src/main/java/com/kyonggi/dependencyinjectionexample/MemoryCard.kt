package com.kyonggi.dependencyinjectionexample

import android.util.Log
import javax.inject.Inject

class MemoryCard @Inject constructor(){

    init {
        Log.i("TAG", "메모리 카드 생성완료!")
    }

    fun getMemorySpaceAvailability() {
        Log.i("TAG", "메모리 카드 저장공간 확보!")
    }

}
