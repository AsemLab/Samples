package com.asemlab.activity_recognition.ui.profile

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.asemlab.activity_recognition.R
import com.asemlab.activity_recognition.databinding.FragmentProfileBinding
import com.asemlab.activity_recognition.ui.dialogs.TwoButtonsDialog
import dagger.hilt.android.AndroidEntryPoint

// Developed by: Asem Abu alrub // AsemLab © 2025

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val profileViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding) {

            val roundImage = RoundedBitmapDrawableFactory.create(
                resources,
                BitmapFactory.decodeResource(resources, R.drawable.avatar)
            ).apply {
                cornerRadius = 50f
                isCircular = true
            }
            profileImage.setImageDrawable(roundImage)

            deleteButton.setOnClickListener {
                TwoButtonsDialog.Builder()
                    .setTitle("Confirm deleting")
                    .setMessage("Are you sure to delete all data?")
                    .setOkButton("Yes") { _ ->
                        profileViewModel.clearData()
                        findNavController().navigateUp()
                    }.setCloseButton("No") { _ -> }
                    .build()
                    .show(parentFragmentManager, "DeleteDialog")
            }

        }
        return root
    }

}