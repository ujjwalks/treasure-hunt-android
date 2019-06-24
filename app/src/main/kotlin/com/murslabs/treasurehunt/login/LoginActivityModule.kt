package com.murslabs.treasurehunt.login

import android.content.Context
import com.murslabs.treasurehunt.dependency.module.BaseActivityModule
import com.murslabs.treasurehunt.dependency.qualifiers.ActivityContext
import com.murslabs.treasurehunt.dependency.scope.PerActivity
import dagger.Binds
import dagger.Module

@Module
abstract class LoginActivityModule : BaseActivityModule() {

    @Binds
    @PerActivity
    @ActivityContext
    abstract fun provideActivityContext(loginActivity: LoginActivity): Context
}