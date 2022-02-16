package com.kyonggi.dependencyinjectionexample

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MemoryCardModule::class, MyBatteryModule::class])
interface SmartPhoneComponent {
    fun inject(mainActivity: MainActivity)
}