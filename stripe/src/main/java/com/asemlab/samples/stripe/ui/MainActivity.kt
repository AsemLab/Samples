package com.asemlab.samples.stripe.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.asemlab.samples.stripe.BuildConfig
import com.asemlab.samples.stripe.R
import com.asemlab.samples.stripe.databinding.ActivityMainBinding
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var paymentSheet: PaymentSheet
    private lateinit var customerConfig: PaymentSheet.CustomerConfiguration
    private lateinit var clientSecret: String
    private lateinit var ephemeralKey: String
    val customerId = BuildConfig.CUSTOMER_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        paymentSheet = PaymentSheet(this) { paymentSheetResult ->
            // TODO Used to handle the flow of the payment
            when (paymentSheetResult) {
                is PaymentSheetResult.Canceled -> {
                    Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
                }

                is PaymentSheetResult.Failed -> {
                    Toast.makeText(
                        this,
                        "Error: ${paymentSheetResult.error.message}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    Log.e("MainActivity", "${paymentSheetResult.error.message}")
                }

                is PaymentSheetResult.Completed -> {
                    // Display for example, an order confirmation screen
                    Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show()
                }
            }
            binding.pay.isEnabled = false
            // TODO Create new Ephemeral key, cuz every PaymentIntent requires unique key
            createEphemeralKey()
        }

        createEphemeralKey()
        with(binding) {
            pay.setOnClickListener {
                presentPaymentSheet()
            }
        }
    }

    private fun createEphemeralKey() {
        mainViewModel.createEphemeralKey(customerId, {
            ephemeralKey = it
//           Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

            mainViewModel.createPaymentIntent(customerId, 250, "eur", { secret ->
                clientSecret = secret
//              Toast.makeText(this, secret, Toast.LENGTH_SHORT).show()

                customerConfig = PaymentSheet.CustomerConfiguration(customerId, ephemeralKey)
                val publishableKey = BuildConfig.STRIPE_PUBLISHABLE_KEY
                PaymentConfiguration.init(this, publishableKey)

                binding.pay.isEnabled = true
            }, onError = { errorMessage ->
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            })


        }, onError = { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })
    }

    private fun presentPaymentSheet() {
        try {
            paymentSheet.presentWithPaymentIntent(
                clientSecret,
                PaymentSheet.Configuration(
                    merchantDisplayName = "Stripe Test Payment",
                    customer = customerConfig,
                    allowsDelayedPaymentMethods = true
                )
            )
        } catch (e: Exception) {
            Log.e("MainActivity", "${e.message}")
        }
    }

}