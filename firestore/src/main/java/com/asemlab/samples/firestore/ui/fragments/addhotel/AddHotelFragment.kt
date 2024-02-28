package com.asemlab.samples.firestore.ui.fragments.addhotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asemlab.samples.firestore.R
import com.asemlab.samples.firestore.databinding.FragmentAddHotelBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddHotelFragment : Fragment() {


    private val addHotelViewModel by viewModels<AddHotelViewModel>()
    private lateinit var binding: FragmentAddHotelBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddHotelBinding.inflate(inflater, container, false)

        with(binding) {
            lifecycleOwner = this@AddHotelFragment
            viewModel = addHotelViewModel

            wifiRG.setOnCheckedChangeListener { _, checkedId ->
                addHotelViewModel.haveWifi.value = checkedId == R.id.haveWifi
            }
            poolRG.setOnCheckedChangeListener { _, checkedId ->
                addHotelViewModel.havePool.value = checkedId == R.id.havePool
            }
            addHotelButton.setOnClickListener {
                addHotelViewModel.insertHotel(addHotelViewModel.getNewHotel(), {
                    findNavController().navigateUp()
                },{
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                })
            }

        }

        with(addHotelViewModel) {
            hotelName.observe(viewLifecycleOwner) {
                binding.addHotelButton.isEnabled = it.isNotEmpty()
            }
        }

        return binding.root
    }

}