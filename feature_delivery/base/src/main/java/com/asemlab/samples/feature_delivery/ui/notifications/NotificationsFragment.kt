package com.asemlab.samples.feature_delivery.ui.notifications

import android.content.Intent
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
    private val moduleName by lazy { getString(R.string.module_notifications) }

    // TODO Listener used to handle changes in state for install requests.
    private val listener = SplitInstallStateUpdatedListener { state ->
        val multiInstall = state.moduleNames().size > 1
        val names = state.moduleNames().joinToString(" - ")
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                displayLoadingState(state, "Downloading $names")
            }

            SplitInstallSessionStatus.INSTALLED -> {
                displayButtons()
                launchNotificationsActivity()
            }

            SplitInstallSessionStatus.INSTALLING -> displayLoadingState(state, "Installing $names")
            SplitInstallSessionStatus.FAILED -> {
                makeToast("Error: ${state.errorCode()} for module ${state.moduleNames()}")
            }

            SplitInstallSessionStatus.CANCELED -> {
                makeToast("Install canceled for module ${state.moduleNames()}")
                displayButtons()
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

            uninstallButton.isVisible = manager.installedModules.contains(moduleName)
            downloadButton.isVisible = !manager.installedModules.contains(moduleName)

            uninstallButton.setOnClickListener {
                if (manager.installedModules.contains(moduleName))
                    // TODO Remove installed modules
                    manager.deferredUninstall(listOf(moduleName))
            }

            downloadButton.setOnClickListener {

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
                    launchNotificationsActivity()
                }
            }
        }

        // TODO You can access base module without any additional configuration
//        HomeFragment()

        return binding.root
    }

    private fun launchNotificationsActivity() {
        val notificationsAct =
            "${requireContext().packageName}.notifications.MainActivity"
        Intent().setClassName(requireContext().packageName, notificationsAct)
            .also {
                startActivity(it)
            }
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
        binding.uninstallButton.isVisible = false
        binding.cancelButton.isVisible = true
    }

    private fun displayButtons() {
        binding.progressGroup.isVisible = false
        binding.cancelButton.isVisible = false
        binding.uninstallButton.isVisible = manager.installedModules.contains(moduleName)
        binding.downloadButton.isVisible = !manager.installedModules.contains(moduleName)
    }

}