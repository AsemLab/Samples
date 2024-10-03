package com.asemlab.samples.feature_delivery.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.asemlab.quakes.R
import com.asemlab.quakes.databinding.FragmentNotificationsBinding
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private val viewModel by viewModels<NotificationsViewModel>()
    private lateinit var manager: SplitInstallManager
    private var sessionId = 0

    // TODO Listener used to handle changes in state for install requests.
    private val listener = SplitInstallStateUpdatedListener { state ->
        val multiInstall = state.moduleNames().size > 1
        val names = state.moduleNames().joinToString(" - ")
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                displayLoadingState(state, "Downloading $names")
            }

            SplitInstallSessionStatus.INSTALLED -> {
                displayButton()
            }

            SplitInstallSessionStatus.INSTALLING -> displayLoadingState(state, "Installing $names")
            SplitInstallSessionStatus.FAILED -> {
                makeToast("Error: ${state.errorCode()} for module ${state.moduleNames()}")
            }

            SplitInstallSessionStatus.CANCELED -> {
                makeToast("Install canceled for module ${state.moduleNames()}")
                displayButton()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        manager = SplitInstallManagerFactory.create(requireContext())

        with(binding) {

            cancelButton.setOnClickListener {
                manager.cancelInstall(sessionId)
            }

            downloadButton.setOnClickListener {
                val moduleName = getString(R.string.module_notifications)

                // TODO Check if module is installed
                if (!manager.installedModules.contains(moduleName)) {
                    // TODO Download and install module
                    val request = SplitInstallRequest.newBuilder()
                        .addModule(moduleName).build()

                    manager.startInstall(request)
                        .addOnSuccessListener { id ->
                            sessionId = id
                        }
                    // TODO Add those listeners (optional)

//                        .addOnFailureListener { e ->
//                            makeToast("Error installing module: ${e.message}")
//                        }
//                        .addOnCompleteListener {
//                            makeToast("Complete installing")
//                        }

                    // TODO Install module in future
//                    manager.deferredInstall(listOf(moduleName))
                } else {
                    makeToast("Module is installed")
                }
            }
        }

        return binding.root
    }

    // TODO Register/Unregister status listener
    override fun onResume() {
        super.onResume()
        manager.registerListener(listener)
    }

    override fun onPause() {
        super.onPause()
        manager.unregisterListener(listener)
    }

    fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun displayLoadingState(state: SplitInstallSessionState, message: String) {
        displayProgress()

        binding.progressBar.max = state.totalBytesToDownload().toInt()
        binding.progressBar.progress = state.bytesDownloaded().toInt()

        updateProgressMessage(message)
    }

    private fun updateProgressMessage(message: String) {
        if (!binding.progressGroup.isVisible)
            displayProgress()
        binding.progressTitle.text = message
    }

    private fun displayProgress() {
        binding.progressGroup.isVisible = true
        binding.downloadButton.isVisible = false
        binding.cancelButton.isVisible = true
    }

    private fun displayButton() {
        binding.progressGroup.isVisible = false
        binding.downloadButton.isVisible = true
        binding.cancelButton.isVisible = false
    }

}