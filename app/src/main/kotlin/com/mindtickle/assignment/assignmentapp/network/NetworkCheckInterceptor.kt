package com.mindtickle.assignment.assignmentapp.network

import com.mindtickle.assignment.assignmentapp.exceptions.NoNetworkException
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