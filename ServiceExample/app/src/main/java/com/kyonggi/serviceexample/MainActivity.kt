package com.kyonggi.serviceexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kyonggi.serviceexample.databinding.ActivityMainBinding
import androidx.core.app.JobIntentService.enqueueWork
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val SERVICE_ID = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun buttonClick(view: View) {
//        enqueueWork(this, MyJobIntentService::class.java, SERVICE_ID, intent)
        intent = Intent(this, MyService::class.java)
        startService(intent)
    }
}