package com.asemlab.samples.appshortcuts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asemlab.samples.appshortcuts.databinding.FragmentBinding

class BusinessFragment : Fragment() {
    private lateinit var binding: FragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBinding.inflate(inflater, container, false)
        binding.title.text = "Business"
        binding.toActivity2.text = "Open Business page"

        return binding.root
    }

}