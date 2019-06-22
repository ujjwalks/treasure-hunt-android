package com.murslabs.treasurehunt.data.local.prefs

/**
 * Created by Ujjwal on 07/02/18.
 */
interface PrefHelper {

    var userLoggedIn: Boolean?

    var currentUserId: String?

    var currentUserName: String?

    var currentUserEmail: String?

    var currentUserProfilePicUrl: String?

    var currentAccessToken: String?
}