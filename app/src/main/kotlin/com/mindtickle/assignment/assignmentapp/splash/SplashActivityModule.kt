package com.mindtickle.assignment.assignmentapp.splash

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.mindtickle.assignment.assignmentapp.dependency.key.ViewModelKey
import com.mindtickle.assignment.assignmentapp.dependency.module.BaseActivityModule
import com.mindtickle.assignment.assignmentapp.dependency.qualifiers.ActivityContext
import com.mindtickle.assignment.assignmentapp.dependency.scope.PerActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Ujjwal on 05/02/18.
 */
@Module
abstract class SplashActivityModule : BaseActivityModule() {

    @Binds
    @PerActivity
    @ActivityContext
    abstract fun provideActivityContext(splashActivity: SplashActivity): Context

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindsSplashFragmentViewModel(splashViewModel: SplashViewModel): ViewModel
}