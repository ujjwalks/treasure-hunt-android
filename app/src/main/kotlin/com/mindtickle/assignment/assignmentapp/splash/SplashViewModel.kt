package com.mindtickle.assignment.assignmentapp.splash

import com.mindtickle.assignment.assignmentapp.base.BaseViewModel
import com.mindtickle.assignment.assignmentapp.utils.rx.applyIOMainSchedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    var isUserLoggedIn: BehaviorSubject<Boolean> = BehaviorSubject.create()

    override fun refresh() {
    }
}