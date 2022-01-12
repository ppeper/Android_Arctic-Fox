package com.kyonggi.viewmodeldemo2.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import com.kyonggi.viewmodeldemo2.R
import com.kyonggi.viewmodeldemo2.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.application?.let {
            val factory = SavedStateViewModelFactory(it, this)
            viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

            val resultObserver =
                Observer<Float> { result -> binding.resultText.text = result.toString() }

            viewModel.getResult().observe(viewLifecycleOwner, resultObserver)

            binding.convertButton.setOnClickListener {
                if (binding.dollarText.text.isNotEmpty()) {
                    viewModel.setAmount(binding.dollarText.text.toString())
                } else {
                    binding.resultText.text = "No Value"
                }
            }
        }
    }
}