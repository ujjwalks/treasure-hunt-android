package com.murslabs.treasurehunt.network

import com.murslabs.treasurehunt.exceptions.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkCheckInterceptor @Inject constructor(val networkMonitor: NetworkMonitor) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (networkMonitor.isConnected()) {
            chain.proceed(chain.request())
        } else {
            throw NoNetworkException()
        }
    }
}