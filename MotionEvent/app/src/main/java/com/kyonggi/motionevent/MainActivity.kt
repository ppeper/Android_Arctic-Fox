package com.kyonggi.motionevent

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.kyonggi.motionevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityMain.setOnTouchListener { view, motionEvent ->
            handleTouch(motionEvent)
            true
        }
    }

    private fun handleTouch(motionEvent: MotionEvent?) {
        val pointerCount = motionEvent?.pointerCount
        for (i in 0 until pointerCount!!) {
            val x = motionEvent.getX(i)
            val y = motionEvent.getY(i)
            val id = motionEvent.getPointerId(i)
            val actionIndex = motionEvent.actionIndex
            var actionString: String = when (motionEvent.actionMasked) {
                MotionEvent.ACTION_DOWN -> "DOWN"
                MotionEvent.ACTION_UP -> "UP"
                MotionEvent.ACTION_POINTER_DOWN -> "PNTR DOWN"
                MotionEvent.ACTION_POINTER_UP -> "PNTR UP"
                else -> ""
            }
            val touchStatus =
                "Action: $actionString Index: $actionIndex ID: $id X: $x Y: $y"
            if (id == 0) {
                binding.textView1.text = touchStatus
            } else {
                binding.textView2.text = touchStatus
            }
        }
    }
}