package com.kyonggi.dependencyinjectionexample

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule(val memorySize: Int) {

    @Provides
    fun providesMemoryCard(): MemoryCard {
        Log.i("TAG", "메모리 용량 : $memorySize")
        return MemoryCard()
    }
}