package com.asemlab.samples.navigation_component.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.NavDeepLinkBuilder
import com.asemlab.samples.navigation_component.R
import com.asemlab.samples.navigation_component.databinding.FragmentBinding

/**
 * TODO Implement Explicit deep link
 */
class SportsFragment : Fragment() {
    private lateinit var binding: FragmentBinding
    private val fragmentsVM by activityViewModels<FragmentsVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBinding.inflate(inflater, container, false)
        binding.title.text = "Sports"
        binding.t1.text = "Click to create deep link notification"
        binding.newsSections.setOnClickListener {
            createDeepLinkNotification()
        }
        binding.title.setOnClickListener {
            fragmentsVM.setTitle("New Sport title")
        }

        fragmentsVM.title.observe(viewLifecycleOwner) {
            binding.title.text = it
        }

        // TODO Pass results between fragments
        setFragmentResult("has_open_sports", bundleOf("open_sports" to true))
        requireActivity().supportFragmentManager.setFragmentResult(
            "has_open_sports",
            bundleOf("open_sports" to true)
        )

        return binding.root
    }

    private fun createDeepLinkNotification() {
        val pendingIntent = NavDeepLinkBuilder(requireContext())
            .setGraph(R.navigation.main_graph)
            .setDestination(
                R.id.internationalDetailsFragment,
                bundleOf("id" to "404", "pageBackground" to R.color.white)
            )
            .createPendingIntent()

        val notification = NotificationCompat.Builder(requireContext())
            .setContentTitle("Test Deep Link")
            .setContentText("Click to navigate to News Details")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_launcher_foreground)

        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "navigation_channel",
                "Navigation Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
            notification.setChannelId("navigation_channel")
        }

        notificationManager.notify(10001, notification.build())
    }

}