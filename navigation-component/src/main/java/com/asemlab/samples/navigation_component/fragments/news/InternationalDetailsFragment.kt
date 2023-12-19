package com.asemlab.samples.navigation_component.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.asemlab.samples.navigation_component.R
import com.asemlab.samples.navigation_component.databinding.FragmentInternationalDetailsBinding

class InternationalDetailsFragment : Fragment() {

    private lateinit var binding: FragmentInternationalDetailsBinding
    // TODO Get arguments using Safe Args
    private val args by navArgs<InternationalDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInternationalDetailsBinding.inflate(inflater, container, false)

        binding.root.setBackgroundColor(ResourcesCompat.getColor(resources, args.pageBackground, null))

        return binding.root
    }

    // TODO Shared element transitions to a fragment destination
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.move)
    }
}