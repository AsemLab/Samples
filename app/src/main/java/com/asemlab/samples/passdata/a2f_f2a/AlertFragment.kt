package com.asemlab.samples.passdata.a2f_f2a

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.asemlab.samples.databinding.Frag2Binding
import com.asemlab.samples.passdata.a2a.FirstViewModel

class AlertFragment(val title: String = "") : Fragment() {

    private val viewModel by activityViewModels<FirstViewModel>()
    private lateinit var binding: Frag2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = Frag2Binding.inflate(inflater, container, false)

        with(binding) {

            title.text = if (this@AlertFragment.title.isEmpty()) {
                arguments?.getString("title")
            } else
                this@AlertFragment.title

            title.setOnClickListener {
                // Send data to activity
                viewModel.title.postValue("New title")
            }

        }

        return binding.root
    }

    fun updateTitle(s: String) {
        binding.title.text = s
    }
}