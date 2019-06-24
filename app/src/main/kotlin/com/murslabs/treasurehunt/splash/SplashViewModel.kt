package com.murslabs.treasurehunt.splash

import com.google.firebase.auth.FirebaseAuth
import com.murslabs.treasurehunt.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    fun isUserLoggedIn(): Boolean = FirebaseAuth.getInstance().currentUser != null

    override fun refresh() {
    }
}