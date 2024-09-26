package com.asemlab.samples.autofill.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.asemlab.samples.autofill.AutoFillApp
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import java.util.regex.Pattern


/**
 * BroadcastReceiver to wait for SMS messages. This can be registered either
 * in the AndroidManifest or at runtime.  Should filter Intents on
 * SmsRetriever.SMS_RETRIEVED_ACTION.
 */
class SMSReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("SMS Receiver", "OnReceive")

        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
            val extras = intent.extras
            val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

            when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    // TODO 5. Get SMS message contents and parse the otp
                    val message = extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE)
                    val pattern = (Pattern.compile("Your code is: (\\d{4}).*"))
                    message?.let {
                        val matcher = pattern.matcher(it)
                        if (matcher.find()) {
                            (context.applicationContext as AutoFillApp).otp.postValue(matcher.group(1))
                        }
                    }

                    Log.d("SMS Receiver", "message: $message")
                }

                CommonStatusCodes.TIMEOUT -> {
                    Log.e("SMS Receiver", "Timeout: ${status.statusMessage}")
                }
            }
        }
    }
}