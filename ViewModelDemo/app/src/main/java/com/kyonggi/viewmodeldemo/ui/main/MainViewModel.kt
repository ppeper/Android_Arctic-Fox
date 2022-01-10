package com.kyonggi.viewmodeldemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val usd_to_eu_rate = 0.74f
//    private var dollarText = ""
//    private var result: Float = 0f
    var result: MutableLiveData<Float> = MutableLiveData()
    var dollarValue: MutableLiveData<String> = MutableLiveData()

//    fun setAmount(value: String) {
//        this.dollarText = value
////        result = value.toFloat() * usd_to_eu_rate
//        result.setValue(value.toFloat() * usd_to_eu_rate)
//    }

//    fun getResult(): MutableLiveData<Float> {
//        return result
//    }

    fun convertValue() {
        dollarValue.let {
            if (!it.value.equals("")) {
                result.value = it.value?.toFloat()?.times(usd_to_eu_rate)
            } else {
                result.value = 0f
            }
        }
    }
}