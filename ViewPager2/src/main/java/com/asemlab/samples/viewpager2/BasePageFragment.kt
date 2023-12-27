package com.asemlab.samples.viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.asemlab.samples.viewpager2.databinding.FragmentBaseBinding

class BasePageFragment private constructor(private val title: String): Fragment() {

    private lateinit var binding: FragmentBaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseBinding.inflate(inflater, container, false)
        binding.title.text = title

        return binding.root
    }

    companion object {
        const val TITLE_KEY = "title"

        fun getInstance(title: String) = BasePageFragment(title)
    }

}