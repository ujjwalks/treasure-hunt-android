package com.mindtickle.assignment.assignmentapp.data.local.prefs

import javax.inject.Inject
import android.content.SharedPreferences
/**
 * Created by Ujjwal on 07/02/18.
 */

class AppPrefHelper @Inject
constructor(val prefs: SharedPreferences) : PrefHelper {
    override var userLoggedIn: Boolean?
        get() = prefs.getBoolean(PREF_KEY_IS_USER_LOGGED_IN, false)
        set(loggedIn) {
            prefs.edit().putBoolean(PREF_KEY_IS_USER_LOGGED_IN, loggedIn!!).apply()
        }

    override var currentUserId: String?
        get() = prefs.getString(PREF_KEY_CURRENT_USER_ID, null)
        set(userId) = prefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userId).apply()

    override var currentUserName: String?
        get() = prefs.getString(PREF_KEY_CURRENT_USER_NAME, null)
        set(userName) = prefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply()

    override var currentUserEmail: String?
        get() = prefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
        set(email) = prefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()

    override var currentUserProfilePicUrl: String?
        get() = prefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null)
        set(profilePicUrl) =
            prefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply()

    override var currentAccessToken: String?
        get() = prefs.getString(PREF_KEY_CURRENT_ACCESS_TOKEN, null)
        set(profilePicUrl) =
            prefs.edit().putString(PREF_KEY_CURRENT_ACCESS_TOKEN, profilePicUrl).apply()

    companion object {
        private const val PREF_KEY_IS_USER_LOGGED_IN = "PREF_KEY_IS_USER_LOGGED_IN"
        private const val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
        private const val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
        private const val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
        private const val PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL"
        private const val PREF_KEY_CURRENT_ACCESS_TOKEN = "PREF_KEY_CURRENT_ACCESS_TOKEN"
    }
}