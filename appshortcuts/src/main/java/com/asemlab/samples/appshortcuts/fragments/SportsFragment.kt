package com.asemlab.samples.appshortcuts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.asemlab.samples.appshortcuts.databinding.FragmentBinding

class SportsFragment : Fragment() {
    private lateinit var binding: FragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBinding.inflate(inflater, container, false)
        binding.title.text = "Sports"
        binding.toActivity2.text = "Open Sports page"
        binding.toActivity2.isVisible = false

        return binding.root
    }

}