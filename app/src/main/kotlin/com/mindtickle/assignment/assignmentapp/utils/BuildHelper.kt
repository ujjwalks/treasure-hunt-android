package com.mindtickle.assignment.assignmentapp.utils

import android.os.Build
import com.mindtickle.assignment.assignmentapp.BuildConfig
import javax.inject.Inject

class BuildHelper @Inject constructor() {
    fun getVersionCodeName(): String {
        return Build.VERSION.CODENAME
    }

    fun getVersionIncremental(): String {
    return Build.VERSION.INCREMENTAL
    }

    fun getVersionRelease(): String {
    return Build.VERSION.RELEASE
    }

    fun getVersionName(): String {
        return BuildConfig.VERSION_NAME
    }
    fun getSDKVersion(): Int {
        return Build.VERSION.SDK_INT
    }
}