package com.mindtickle.assignment.assignmentapp.dependency.module

import android.accounts.AccountManager
import android.content.Context
import com.mindtickle.assignment.assignmentapp.dependency.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class SystemServiceModule {

    @Provides
    @PerApplication
    fun providesAccountManager(context: Context): AccountManager {
        return AccountManager.get(context)
    }
}