package com.asemlab.samples.navigation_component.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
//            findNavController().navigate(R.id.action_internationalFragment_to_internationalDetailsFragment)
            // TODO Use Safe Args for navigation
            findNavController().navigate(InternationalFragmentDirections.actionInternationalFragmentToInternationalDetailsFragment())
        }

        // TODO Get arguments from deep link
        val id =arguments?.getString("id", "-1") ?: "-99"
        Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()


        return binding.root
    }

}