package com.kyonggi.commongestures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import com.kyonggi.commongestures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private lateinit var binding: ActivityMainBinding
    var gDetector: GestureDetectorCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Detector 생성
        this.gDetector = GestureDetectorCompat(this, this)
        gDetector?.setOnDoubleTapListener(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.gDetector?.onTouchEvent(event)
        // 슈퍼 클래스의 오버라이딩되는 함수를 호출해야 한다
        return super.onTouchEvent(event)
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        binding.gestureStatusText.text = "onDown"
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {
        binding.gestureStatusText.text = "onShowPress"
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        binding.gestureStatusText.text = "onSingleTapUp"
        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        binding.gestureStatusText.text = "onScroll"
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {
        binding.gestureStatusText.text = "onLongPress"
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        binding.gestureStatusText.text = "onFling"
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
        binding.gestureStatusText.text = "onSingleTapConfirmed"
        return true
    }

    override fun onDoubleTap(p0: MotionEvent?): Boolean {
        binding.gestureStatusText.text = "onDoubleTap"
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
        binding.gestureStatusText.text = "onDoubleTapEvent"
        return true
    }
}