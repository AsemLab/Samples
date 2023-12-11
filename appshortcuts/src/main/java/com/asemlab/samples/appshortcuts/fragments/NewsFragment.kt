package com.asemlab.samples.appshortcuts.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asemlab.samples.appshortcuts.SectionActivity
import com.asemlab.samples.appshortcuts.databinding.FragmentBinding

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBinding.inflate(inflater, container, false)
        binding.title.text = "News"
        binding.toActivity2.text = "Open News page"

        binding.toActivity2.setOnClickListener {
            startActivity(Intent(requireContext(), SectionActivity::class.java))
        }

        return binding.root
    }


}