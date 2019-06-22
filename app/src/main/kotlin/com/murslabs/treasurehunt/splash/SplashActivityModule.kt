package com.murslabs.treasurehunt.splash

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.murslabs.treasurehunt.dependency.key.ViewModelKey
import com.murslabs.treasurehunt.dependency.module.BaseActivityModule
import com.murslabs.treasurehunt.dependency.qualifiers.ActivityContext
import com.murslabs.treasurehunt.dependency.scope.PerActivity
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