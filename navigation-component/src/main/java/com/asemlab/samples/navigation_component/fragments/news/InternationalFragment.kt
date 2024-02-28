package com.asemlab.samples.navigation_component.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.asemlab.samples.navigation_component.R
import com.asemlab.samples.navigation_component.databinding.FragmentInternationalBinding

class InternationalFragment : Fragment() {

    private lateinit var binding: FragmentInternationalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInternationalBinding.inflate(inflater, container, false)
        binding.t1.setOnClickListener {
            // findNavController().navigate(R.id.action_internationalFragment_to_internationalDetailsFragment)
            // TODO Use Safe Args for navigation
            // To be able to send argument in this way, the arguments in the ACTION element, shouldn't have default values
//            findNavController().navigate(InternationalFragmentDirections.actionInternationalFragmentToInternationalDetailsFragment(R.color.yellow))

            // TODO Shared element transitions to a fragment destination
            val extras = FragmentNavigatorExtras(binding.imageView to "newsTitle")

            findNavController().navigate(
                R.id.action_internationalFragment_to_internationalDetailsFragment,
                bundleOf("pageBackground" to R.color.orange), // TODO Pass data between destinations with Bundle objects
                null, // NavOptions
                extras
            )

        }


        return binding.root
    }

}