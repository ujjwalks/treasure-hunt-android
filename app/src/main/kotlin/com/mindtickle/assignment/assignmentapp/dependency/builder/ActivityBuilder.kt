package com.mindtickle.assignment.assignmentapp.dependency.builder

import com.mindtickle.assignment.assignmentapp.dependency.scope.PerActivity
import com.mindtickle.assignment.assignmentapp.home.HomeActivity
import com.mindtickle.assignment.assignmentapp.home.HomeActivityModule
import com.mindtickle.assignment.assignmentapp.splash.SplashActivity
import com.mindtickle.assignment.assignmentapp.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ujjwal on 05/02/18.
 */
@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
    internal abstract fun bindSplashActivity(): SplashActivity


    @ContributesAndroidInjector(modules = [(HomeActivityModule::class)])
    @PerActivity
    internal abstract fun bindHomeActivity(): HomeActivity
}
