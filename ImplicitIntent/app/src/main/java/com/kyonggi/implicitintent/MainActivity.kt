package com.kyonggi.implicitintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kyonggi.implicitintent.databinding.ActivityMainBinding
import java.net.URI
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun showWebPage(view: View) {
        val intent = Intent(Intent.ACTION_VIEW,
        Uri.parse("https://www.ebookfrenzy.com"))
        startActivity(intent)
    }
}