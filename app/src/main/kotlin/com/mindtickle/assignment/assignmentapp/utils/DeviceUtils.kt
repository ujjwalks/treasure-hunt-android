package com.mindtickle.android.utils

import android.content.Context
import android.provider.Settings
import javax.inject.Inject

class DeviceUtils @Inject constructor(
        val context: Context
) {
    fun getDevideId(): String {
        return Settings.Secure.getString(context.contentResolver,
                Settings.Secure.ANDROID_ID)
    }
}