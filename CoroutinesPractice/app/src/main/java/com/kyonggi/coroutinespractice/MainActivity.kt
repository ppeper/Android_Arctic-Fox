package com.kyonggi.coroutinespractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kyonggi.coroutinespractice.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCount.setOnClickListener {
            binding.tvCount.text = count++.toString()
        }

        binding.btnDownload.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                downloadData()
            }
        }
    }

    private fun downloadData() {
        for (i in 1..50000) {
            Log.i("Coroutines", "Download $i in ${Thread.currentThread().name}")
        }
    }
}