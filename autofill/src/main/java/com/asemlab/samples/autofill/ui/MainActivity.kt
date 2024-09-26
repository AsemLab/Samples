package com.asemlab.samples.autofill.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.asemlab.samples.autofill.AutoFillApp
import com.asemlab.samples.autofill.R
import com.asemlab.samples.autofill.databinding.ActivityMainBinding
import com.asemlab.samples.autofill.utils.AppSignatureHelper
import com.google.android.gms.auth.api.phone.SmsRetriever

/***
 *  TODO This is an otp retrieval demo (get otp from SMS automatically)
 *
 *  the SMS should be in form:
 *  """ Your code is: ####
 *      THE APP_SIGNATURE HASH """
 *
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO 2. Get hash string for this app to added to otp SMS
        val appHash = AppSignatureHelper(this).appSignatures[0]
        Log.d("MainActivity", appHash)

        // TODO 3. Start listening for SMS
        // Get an instance of SmsRetrieverClient, used to start listening for a matching SMS message.
        val client = SmsRetriever.getClient(this)

        // Starts SmsRetriever, which waits for ONE matching SMS message until timeout
        // (5 minutes). The matching SMS message will be sent via a Broadcast Intent with
        // action SmsRetriever#SMS_RETRIEVED_ACTION.
        val task = client.startSmsRetriever()
        task.addOnSuccessListener {
            // Successfully started retriever, expect broadcast intent
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
        task.addOnFailureListener {
            // Failed to start retriever, inspect Exception for more details
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }


        // TODO 6. Insert the otp into the EditText field
        (application as AutoFillApp).otp.observe(this) {
            binding.smsOtp.text.apply {
                clear()
                append(it)
            }

        }

    }
}