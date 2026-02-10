package com.asemlab.samples.activity_recognition.utilties

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message

class TimerUtility(private val interval: Long, private val onTick: () -> Unit) {

    private val MSG = 1
    private val mHandler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {

            synchronized(this@TimerUtility) {
                onTick()
                sendMessageDelayed(obtainMessage(MSG), interval)
            }
        }
    }

    fun cancel() {
        mHandler.removeMessages(MSG)
    }

    fun start() {
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG), interval)
    }

    fun isRunning(): Boolean{
        return mHandler.hasMessages(MSG)
    }


}