package com.murslabs.treasurehunt.home

import android.content.Context
import com.murslabs.treasurehunt.dependency.module.BaseActivityModule
import com.murslabs.treasurehunt.dependency.qualifiers.ActivityContext
import com.murslabs.treasurehunt.dependency.scope.PerActivity
import dagger.Binds
import dagger.Module

@Module
abstract class HomeActivityModule : BaseActivityModule() {

    @Binds
    @PerActivity
    @ActivityContext
    abstract fun provideActivityContext(homeActivity: HomeActivity): Context
}
