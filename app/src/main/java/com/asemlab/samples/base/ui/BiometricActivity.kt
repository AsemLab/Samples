package com.asemlab.samples.base.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.asemlab.samples.base.R
import com.asemlab.samples.base.databinding.ActivityBiometricBinding
import com.asemlab.samples.base.utils.BiometricUtils

// TODO Show a biometric authentication dialog using the Biometric library
class BiometricActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiometricBinding
    private val activityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it?.resultCode == RESULT_OK) {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "CANCELED", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_biometric)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(BiometricUtils) {
            val callback = object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        this@BiometricActivity,
                        "Biometric error: $errorCode, $errString",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(this@BiometricActivity, "Biometric failed!", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(
                        this@BiometricActivity,
                        "Biometric Succeeded!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            initBiometricManager(this@BiometricActivity, callback)
            buildPromptInfo("Login to BiometricApp", "Log in using your fingerprint or face")
        }

        with(binding) {
            loginButton.setOnClickListener {
                BiometricUtils.canAuthenticate(onSuccess = {
                    BiometricUtils.authenticate()
                }, onError = { msg ->
                    Toast.makeText(
                        this@BiometricActivity,
                        "Biometric error: $msg",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    // TODO If the user doesn't enable the biometric authentication, open sittings if supported (optional)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        val enrollIntent =
                            Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                                putExtra(
                                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                                )
                            }
                        activityResult.launch(enrollIntent)
                    }
                }
            }
        }
    }

    // TODO Hide screen content in recent apps screen
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            // Remove when back to activity
            window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        } else {
            // TODO Prevent screenshot
            window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        }
    }
}