package com.kyonggi.sendbroadcast

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kyonggi.sendbroadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // 브로드캐스트 인텐트를 지정해야한다.
    var receiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureReceiver()
    }

    private fun configureReceiver() {
        val filter = IntentFilter()
        filter.addAction("com.kyonggi.sendbroadcast")
        // 장치의 외부 전원이 끊어졌을때
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED")

        receiver = MyReceiver()
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    // 브로드캐스트 인텐트를 전송
    fun broadcastIntent(view: View) {
        val intent = Intent()
        intent.action = "com.kyonggi.sendbroadcast"
        intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
        sendBroadcast(intent)
    }
}