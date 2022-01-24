package com.kyonggi.videoplayer

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.graphics.Picture
import android.graphics.drawable.Icon
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import com.kyonggi.videoplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var TAG = "VideoPlayer"
    private lateinit var binding: ActivityMainBinding
    private var mediaController: MediaController? = null
    private val receiver: BroadcastReceiver? = null
    private val REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureVideoView()
    }

    private fun createPipAction() {
        val actions = ArrayList<RemoteAction>()

        val actionIntent = Intent("com.kyonggi.videoplayer.VIDEO_INFO")
        val pendingIntent = PendingIntent.getBroadcast(
            this@MainActivity, REQUEST_CODE, actionIntent, 0
        )

        val icon = Icon.createWithResource(this, R.drawable.ic_info_24dp)
        val remoteAction = RemoteAction(icon, "Info", "Video Info", pendingIntent)
        actions.add(remoteAction)

        val params = PictureInPictureParams.Builder()
            .setActions(actions)
            .build()

        setPictureInPictureParams(params)
    }

    private fun configureVideoView() {
        binding.videoView1.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.movie))

        mediaController = MediaController(this)
        mediaController?.setAnchorView(binding.videoView1)
        binding.videoView1.setMediaController(mediaController)

        binding.videoView1.setOnPreparedListener { mp ->
            mp.isLooping = true
            Log.i(TAG, "Duration = " + binding.videoView1.duration)
        }
        binding.videoView1.start()
    }

    fun enterPipMode(view: View) {
        val rational = Rational(
            binding.videoView1.width,
            binding.videoView1.height
        )
        val params = PictureInPictureParams.Builder()
            .setAspectRatio(rational)
            .build()

        binding.pipButton.visibility = View.INVISIBLE
        binding.videoView1.setMediaController(null)
        enterPictureInPictureMode(params)
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)

        if (isInPictureInPictureMode) {
            val filter = IntentFilter()
            filter.addAction("com.kyonggi.videoplayer.VIDEO_INFO")

            val receiver = object : BroadcastReceiver() {
                override fun onReceive(p0: Context?, p1: Intent?) {
                    Toast.makeText(p0, "Favorite Home Movie Clips", Toast.LENGTH_SHORT).show()
                }
            }

            registerReceiver(receiver, filter)
            createPipAction()
        } else {
            binding.pipButton.visibility = View.VISIBLE
            binding.videoView1.setMediaController(mediaController)

            receiver?.let {
                unregisterReceiver(it)
            }
        }
    }
}