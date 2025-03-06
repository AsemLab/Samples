package com.asemlab.activity_recognition.ui.dashboard

// Developed by: Asem Abu alrub // AsemLab © 2025

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.asemlab.activity_recognition.ActivityRecognitionApp
import com.asemlab.activity_recognition.R
import com.asemlab.activity_recognition.databinding.FragmentDashboardBinding
import com.asemlab.activity_recognition.model.ActivityEntry
import com.asemlab.activity_recognition.utilties.ActivityDetectionUtility
import com.asemlab.activity_recognition.utilties.ActivityType
import com.asemlab.activity_recognition.utilties.Constants
import com.asemlab.activity_recognition.utilties.DetectingMode
import com.asemlab.activity_recognition.utilties.TimerUtility
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardFragment : Fragment() {


    private lateinit var binding: FragmentDashboardBinding
    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it == true) {
                requireActivity().recreate()
            }
        }
    private var points = 0
    private var pointsFactor = Constants.WALKING_POINTS_FACTOR
    private val timer = TimerUtility(Constants.TIMER_INTERVAL) {
        points += pointsFactor
        (requireContext().applicationContext.applicationContext as ActivityRecognitionApp).currentPoints
            .value = (points)
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
                binding.lastPoints.text = "${it?.points ?: 0}"
            }

            totalPoints.observe(this@DashboardFragment.viewLifecycleOwner) {
                binding.totalPoints.text = "${it ?: 0}"
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
                (requireContext().applicationContext as ActivityRecognitionApp)
                    .detectingMode.postValue(if (!isRunning) DetectingMode.ON else DetectingMode.OFF)

                // TODO Start detecting
                ActivityDetectionUtility.switchDetecting(requireContext())
            }

            // For Testing purposes
            sendActionButton.setOnClickListener {
//                if(pointsFactor == Constants.DRIVING_POINTS_FACTOR)
//                    ActivityDetectionUtility.testSendAction(requireContext())
//                else
                ActivityDetectionUtility.testSendActionDriving(requireContext())
            }

        }


        // TODO Ask user for required permission
        if (activityRecognitionPermissionApproved()) {
            binding.startButton.isEnabled = true
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                permissionRequest.launch(Manifest.permission.ACTIVITY_RECOGNITION)
            }
            binding.startButton.isEnabled = false
        }

        with(requireContext().applicationContext as ActivityRecognitionApp) {
            detectingMode.observe(this@DashboardFragment.viewLifecycleOwner) {
                it?.let {
                    when (it) {
                        DetectingMode.ON -> {
                            timer.start()
                        }

                        DetectingMode.OFF -> {
                            timer.cancel()
                            dashboardViewModel.addActivity(
                                ActivityEntry(
                                    activityType.value.toString(),
                                    currentPoints.value!!.toLong(),
                                    System.currentTimeMillis()
                                )
                            )
                            detectingMode.value = null
                        }

                        DetectingMode.ENTER -> {
                            Toast.makeText(
                                requireContext(),
                                "Entered to ${activityType.value}",
                                Toast.LENGTH_SHORT
                            ).show()
                            currentPoints.value = 0
                            points = 0
                        }

                        // Save to database
                        DetectingMode.EXIT -> {
                            dashboardViewModel.addActivity(
                                ActivityEntry(
                                    activityType.value.toString(),
                                    currentPoints.value!!.toLong(),
                                    System.currentTimeMillis()
                                )
                            )
                        }
                    }
                }
            }

            activityType.observe(this@DashboardFragment.viewLifecycleOwner) {
                pointsFactor = when (it!!) {
                    ActivityType.WALKING -> Constants.WALKING_POINTS_FACTOR
                    ActivityType.DRIVING -> Constants.DRIVING_POINTS_FACTOR
                }
            }

            currentPoints.observe(this@DashboardFragment.viewLifecycleOwner) {
                binding.currentPoints.text = "${it ?: 0}"
            }
        }

        return binding.root
    }


    private fun activityRecognitionPermissionApproved(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACTIVITY_RECOGNITION
            )
        } else
            true
    }

}