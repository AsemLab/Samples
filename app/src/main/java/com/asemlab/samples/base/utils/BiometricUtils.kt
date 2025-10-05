package com.asemlab.samples.base.utils

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

// TODO Must add the Biometric dependency
object BiometricUtils {

    private lateinit var biometricManager: BiometricManager
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    fun initBiometricManager(
        context: AppCompatActivity,
        callback: BiometricPrompt.AuthenticationCallback
    ) {
        // TODO 1. Init the biometric manager
        biometricManager = BiometricManager.from(context)
        executor = ContextCompat.getMainExecutor(context)
        biometricPrompt = BiometricPrompt(context, executor, callback)

    }

    fun buildPromptInfo(
        title: String = "",
        description: String = "",
        negationText: String = "Cancel"
    ) {
        // TODO 2. Build the PromptInfo, used for set info for the dialog
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(description)
            .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
//            .setNegativeButtonText("negationText") // Apply when DEVICE_CREDENTIAL isn't enabled
            .build()
    }

    fun canAuthenticate(
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
        onNotEnabled: () -> Unit
    ) {
        // TODO Check if biometric authentication is supported on the device (optional)
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Log.d("BiometricUtils", "App can authenticate using biometrics.")
                onSuccess()
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Log.e("BiometricUtils", "No biometric features available on this device.")
                onError("No biometric features available on this device.")
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Log.e("BiometricUtils", "Biometric features are currently unavailable.")
                onError("Biometric features are currently unavailable.")
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Log.e("BiometricUtils", "Biometric not enrolled")
                onNotEnabled()
            }
        }
    }

    // TODO 3. Show the authentication dialog
    fun authenticate() {
        biometricPrompt.authenticate(promptInfo)
    }
}