package com.asemlab.samples.navigation_component.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asemlab.samples.navigation_component.databinding.FragmentInternationalDetailsBinding

class InternationalDetailsFragment : Fragment() {

    private lateinit var binding: FragmentInternationalDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInternationalDetailsBinding.inflate(inflater, container, false)


        return binding.root
    }

}