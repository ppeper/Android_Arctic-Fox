package com.kyonggi.fragmentexample

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.kyonggi.fragmentexample.databinding.FragmentToolbarBinding
import java.lang.ClassCastException


class ToolbarFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private var _binding: FragmentToolbarBinding? = null
    private val binding get() = _binding!!
    // seekBar
    var seekValue = 10

    var activityCallback: ToolbarFragment.ToolbarListener? = null

    // Interface
    interface ToolbarListener {
        fun onButtonClick(position: Int, text: String)
    }

    // Fragment Attatch
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallback = context as ToolbarListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement ToolbarListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentToolbarBinding.inflate(inflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_toolbar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.seekBar1.setOnSeekBarChangeListener(this)
        binding.button.setOnClickListener { v: View -> buttonClicked(v)}
    }

    private fun buttonClicked(view: View) {
        activityCallback?.onButtonClick(seekValue,
        binding.editText1.text.toString())
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        seekValue = p1
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
}