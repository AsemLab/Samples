package com.asemlab.samples.navigation_component.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.asemlab.samples.navigation_component.R
import com.asemlab.samples.navigation_component.databinding.FragmentBinding

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentBinding
    private val args by navArgs<NewsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBinding.inflate(inflater, container, false)
        binding.title.text = args.pageTitle

        // TODO Add NavOptions when navigate
        binding.newsSections.setOnClickListener {
            findNavController().navigate(
                R.id.action_news_nav_to_internationalFragment,
                null,
                navOptions {
                    anim {
                        enter = android.R.animator.fade_in
                        exit = android.R.animator.fade_out
                    }
                })
        }

        // TODO Pass results between fragments
        setFragmentResultListener("has_open_sports"){ _, bundle ->
            val hasOpen = bundle.getBoolean("open_sports")
            Toast.makeText(requireContext(), "Sports page has opened: $hasOpen", Toast.LENGTH_SHORT).show()
        }

        // TODO Pass results between parent and child fragments
        setFragmentResultListener("news_heading"){ _, bundle ->
            val heading = bundle.getString("news_details_heading")
            Toast.makeText(requireContext(), "$heading", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }


}