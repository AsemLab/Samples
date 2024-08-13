package com.asemlab.samples.navigation_component.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.asemlab.samples.navigation_component.R
import com.asemlab.samples.navigation_component.databinding.FragmentBinding

class BusinessFragment : Fragment() {
    private lateinit var binding: FragmentBinding
    private val fragmentsVM by activityViewModels<FragmentsVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBinding.inflate(inflater, container, false)
        binding.title.text = "Business"
        binding.t1.text = "Business news"
        binding.t1.setOnClickListener {
//            findNavController().navigate(R.id.action_global_international_details)
//            findNavController().navigate(Uri.parse("http://www.example.com/15"))
            val deepLinkRequest =
                NavDeepLinkRequest.Builder.fromUri(Uri.parse("http://www.example.com/15"))
                    .setAction("HIDE").build()

            // TODO Navigate with DeepLinkRequest
            findNavController().navigate(deepLinkRequest)
        }

        binding.title.setOnClickListener {
            fragmentsVM.setTitle("New Business title")
        }

        fragmentsVM.title.observe(viewLifecycleOwner) {
            binding.title.text = it
        }


        binding.toHotels.isVisible = true

        // TODO Navigate to another module
        binding.toHotels.setOnClickListener {
            findNavController().navigate(R.id.action_business_fragment_to_home_fragment)
        }

        return binding.root
    }

}