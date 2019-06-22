package com.murslabs.treasurehunt.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkMonitor @Inject constructor(val context: Context) {

    fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}