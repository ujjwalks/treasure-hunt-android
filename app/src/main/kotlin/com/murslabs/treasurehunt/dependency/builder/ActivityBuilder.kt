package com.murslabs.treasurehunt.dependency.builder

import com.murslabs.treasurehunt.dependency.scope.PerActivity
import com.murslabs.treasurehunt.home.HomeActivity
import com.murslabs.treasurehunt.home.HomeActivityModule
import com.murslabs.treasurehunt.splash.SplashActivity
import com.murslabs.treasurehunt.splash.SplashActivityModule
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
