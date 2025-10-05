package com.asemlab.samples.base.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.widget.Toast

class NetworkCalls(val context: Context): ConnectivityManager.NetworkCallback() {
    private val TAG = NetworkCalls::class.java.simpleName

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Toast.makeText(context, "onAvailable: $network", Toast.LENGTH_SHORT).show()
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        super.onLosing(network, maxMsToLive)
        Toast.makeText(context, "onLosing: $network", Toast.LENGTH_SHORT).show()
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        Toast.makeText(context, "onLost: $network", Toast.LENGTH_SHORT).show()
    }

    override fun onUnavailable() {
        super.onUnavailable()
        Toast.makeText(context, "onUnavailable", Toast.LENGTH_SHORT).show()

    }
}