package com.asemlab.samples.passdata.f2f

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import com.asemlab.samples.databinding.Frag3Binding
import kotlinx.coroutines.launch
import kotlin.random.Random

class TopFrag : Fragment() {

    private lateinit var binding: Frag3Binding
    private val viewModel by activityViewModels<ThirdViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = Frag3Binding.inflate(inflater, container, false)

        with(binding) {
            title.setOnClickListener {
//                viewModel.bottomCounter.postValue("${Random.nextInt(0, 100)}")

                setFragmentResult("counter", bundleOf("counter" to "${Random.nextInt(0, 100)}"))

            }
        }

        viewModel.topCounter.observe(viewLifecycleOwner) {
            binding.counterTV.text = it
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.topCounterFlow.collect {
                binding.counterTV.text = it
            }
        }

        return binding.root
    }

}