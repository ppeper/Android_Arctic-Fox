package com.kyonggi.fragmentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kyonggi.fragmentexample.databinding.FragmentTextBinding

class TextFragment : Fragment() {

    private var _binding: FragmentTextBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTextBinding.inflate(inflater, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    // Text size Change Method
    fun changeTextProperties(fontSize: Int, text: String) {
        binding.textView2.textSize = fontSize.toFloat()
        binding.textView2.text = text
    }
}