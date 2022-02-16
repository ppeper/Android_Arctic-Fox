package com.kyonggi.dependencyinjectionexample

import dagger.Binds
import dagger.Module
import dagger.Provides

//@Module
//class MyBatteryModule {
//
//    @Provides
//    fun providesMyBattery(myBattery: MyBattery): Battery {
//        return myBattery
//    }
//}
@Module
abstract class MyBatteryModule {
    @Binds
    abstract fun bindMyBattery(myBattery: MyBattery): Battery
}