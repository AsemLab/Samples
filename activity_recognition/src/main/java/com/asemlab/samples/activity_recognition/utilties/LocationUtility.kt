package com.asemlab.samples.activity_recognition.utilties

import android.content.Context
import android.location.LocationManager


fun isLocationEnabled(context: Context): Boolean {

    val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?

    val isGpsEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val isNetworkEnabled =
        locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    return isNetworkEnabled || isGpsEnabled

}