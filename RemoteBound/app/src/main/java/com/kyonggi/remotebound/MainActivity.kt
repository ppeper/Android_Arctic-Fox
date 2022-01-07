package com.kyonggi.remotebound

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.view.View
import com.kyonggi.remotebound.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var myService: Messenger? = null
    var isBound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(applicationContext, RemoteService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
    }

    fun sendMessage(view: View) {
        if (!isBound) {
            return
        }
        val msg = Message.obtain()
        val bundle = Bundle()

        bundle.putString("MyString", "Message Received")

        msg.data = bundle

        try {
            myService?.send(msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            myService = Messenger(service)
            isBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            myService = null
            isBound = false
        }
    }
}