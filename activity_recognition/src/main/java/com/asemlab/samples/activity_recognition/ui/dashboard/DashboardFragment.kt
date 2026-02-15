package com.asemlab.samples.activity_recognition.ui.dashboard

// Developed by: Asem Abu alrub // AsemLab © 2025

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.asemlab.samples.activity_recognition.R
import com.asemlab.samples.activity_recognition.databinding.FragmentDashboardBinding
import com.asemlab.samples.activity_recognition.model.ActivityEntry
import com.asemlab.samples.activity_recognition.services.ActivityTrackingService
import com.asemlab.samples.activity_recognition.utilties.ActivityDetectionUtility
import com.asemlab.samples.activity_recognition.utilties.ActivityType
import com.asemlab.samples.activity_recognition.utilties.DataStoreUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DashboardFragment : Fragment() {


    private lateinit var binding: FragmentDashboardBinding
    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                requireActivity().recreate()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        with(dashboardViewModel) {

            lastEntry.observe(this@DashboardFragment.viewLifecycleOwner) {
                binding.lastSteps.text = "${it?.points ?: 0}"
            }

            totalSteps.observe(this@DashboardFragment.viewLifecycleOwner) {
                binding.totalSteps.text = "${it ?: 0}"
            }

            isServiceRunning.observe(this@DashboardFragment.viewLifecycleOwner) {
                binding.startButton.text =
                    if (it) getString(R.string.stop_title) else getString(R.string.start_title)
            }

        }

        with(binding) {
            startButton.setOnClickListener {
                val isRunning = dashboardViewModel.isServiceRunning.value!!

                dashboardViewModel.isServiceRunning.postValue(!isRunning)

                // TODO Start detecting
                val intent = Intent(context, ActivityTrackingService::class.java)
                if (!isRunning) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        requireContext().startForegroundService(intent)
                    } else {
                        requireContext().startService(intent)
                    }
                } else {
                    requireContext().stopService(intent)

                    dashboardViewModel.addActivity(
                        ActivityEntry(
                            ActivityType.WALKING.name,
                            currentSteps.text.toString().toLong(),
                            System.currentTimeMillis()
                        )
                    )
                    lifecycleScope.launch {
                        DataStoreUtils.setCurrentPoints(requireContext(), 0)
                    }
                }
            }

            // For Testing purposes
//            testing.root.isVisible = BuildConfig.DEBUG
            testing.walking.setOnClickListener {
                ActivityDetectionUtility.testEnterWalking(requireContext())
            }
            testing.driving.setOnClickListener {
                ActivityDetectionUtility.testWalkingToDriving(requireContext())
            }
            testing.toWalking.setOnClickListener {
                ActivityDetectionUtility.testDrivingToWalking(requireContext())
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    DataStoreUtils.getCurrentPoints(requireContext()).collect {
                        binding.currentSteps.text = "$it"
                    }
                }
            }
        }


        // TODO Ask user for required permission
        if (activityRecognitionPermissionApproved()) {
            binding.startButton.isEnabled = true
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                permissionRequest.launch(Manifest.permission.ACTIVITY_RECOGNITION)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    permissionRequest.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
            binding.startButton.isEnabled = false
        }


        return binding.root
    }


    private fun activityRecognitionPermissionApproved(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.ACTIVITY_RECOGNITION
                ) && PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.POST_NOTIFICATIONS
                )
            } else {
                PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.ACTIVITY_RECOGNITION
                )
            }
        } else
            true
    }

}