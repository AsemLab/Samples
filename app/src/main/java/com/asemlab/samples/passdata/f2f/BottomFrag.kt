package com.asemlab.samples.passdata.f2f

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.asemlab.samples.databinding.Frag3Binding
import kotlinx.coroutines.launch
import kotlin.random.Random

class BottomFrag : Fragment() {

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
                lifecycleScope.launch {
                    viewModel.topCounterFlow.emit("${Random.nextInt(0, 100)}")
                }
            }
        }

        viewModel.bottomCounter.observe(viewLifecycleOwner) {
            binding.counterTV.text = it
        }

        setFragmentResultListener("counter") { s, b ->
            binding.counterTV.text = b.getString(s)
        }

        return binding.root
    }

}