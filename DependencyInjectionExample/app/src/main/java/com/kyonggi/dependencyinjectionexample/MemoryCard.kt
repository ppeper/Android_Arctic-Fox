package com.kyonggi.dependencyinjectionexample

import android.util.Log

// third party 라이브러리하고 생각
class MemoryCard {

    init {
        Log.i("TAG", "메모리 카드 생성완료!")
    }

    fun getMemorySpaceAvailability() {
        Log.i("TAG", "메모리 카드 저장공간 확보!")
    }

}
