package com.kyonggi.audioapp

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kyonggi.audioapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val RECORD_REQUEST_CODE = 101
    private val STORAGE_REQUEST_CODE = 102

    private lateinit var binding: ActivityMainBinding
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var audioFilePath: String? = null
    private var isRecording = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        audioSetup()
    }


    private fun audioSetup() {
        if (!hasMicrophone()) {
            binding.stopButton.isEnabled = false
            binding.playButton.isEnabled = false
            binding.recordButton.isEnabled = false
        } else {
            binding.playButton.isEnabled = false
            binding.stopButton.isEnabled = false
        }

        audioFilePath = this.getExternalFilesDir(Environment.DIRECTORY_MUSIC)?.absolutePath + "/myaudio.3gp"
        requestPermission(Manifest.permission.RECORD_AUDIO, RECORD_REQUEST_CODE)
    }

    private fun hasMicrophone(): Boolean {
        val packageManager = this.packageManager
        return packageManager.hasSystemFeature(
            PackageManager.FEATURE_MICROPHONE
        )
    }

    fun recordAudio(view: View) {
        isRecording = true
        binding.stopButton.isEnabled = true
        binding.playButton.isEnabled = false
        binding.recordButton.isEnabled = false

        try {
            // MediaRecorder() deprecated -> MediaRecorder(Context: context) instead
            mediaRecorder = MediaRecorder()
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(
                MediaRecorder.OutputFormat.THREE_GPP
            )
            mediaRecorder?.setOutputFile(audioFilePath)
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mediaRecorder?.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mediaRecorder?.start()
    }

    fun stopAudio(view: View) {
        binding.stopButton.isEnabled = false
        binding.playButton.isEnabled = true

        if (isRecording) {
            binding.recordButton.isEnabled = false
            mediaRecorder?.stop()
            mediaRecorder?.release()
            mediaRecorder = null
            isRecording = false
        } else {
            mediaPlayer?.release()
            mediaPlayer = null
            binding.recordButton.isEnabled = true
        }
    }

    fun playAudio(view: View) {
        binding.playButton.isEnabled = false
        binding.recordButton.isEnabled = false
        binding.stopButton.isEnabled = true

        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(audioFilePath)
        mediaPlayer?.prepare()
        mediaPlayer?.start()
    }

    private fun requestPermission(permissionType: String, requestCode: Int) {
        val permission = ContextCompat.checkSelfPermission(this, permissionType)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permissionType), requestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    binding.recordButton.isEnabled = false
                    Toast.makeText(this, "Record permission required", Toast.LENGTH_SHORT).show()
                } else {
                    requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_REQUEST_CODE)
                }
                return
            }

            STORAGE_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "External Storage permission requred", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }

    }
}
