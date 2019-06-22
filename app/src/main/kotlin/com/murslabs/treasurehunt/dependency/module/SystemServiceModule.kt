package com.murslabs.treasurehunt.dependency.module

import android.accounts.AccountManager
import android.content.Context
import com.murslabs.treasurehunt.dependency.scope.PerApplication
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