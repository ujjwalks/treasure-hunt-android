package com.murslabs.treasurehunt.splash

import com.murslabs.treasurehunt.base.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    var isUserLoggedIn: BehaviorSubject<Boolean> = BehaviorSubject.create()

    override fun refresh() {
    }
}