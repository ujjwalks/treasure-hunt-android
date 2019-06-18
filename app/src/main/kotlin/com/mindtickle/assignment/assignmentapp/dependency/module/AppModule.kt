package com.mindtickle.assignment.assignmentapp.dependency.module

import android.app.Application
import android.content.Context
import android.provider.Settings
import com.mindtickle.assignment.assignmentapp.data.local.prefs.AppPrefHelper
import com.mindtickle.assignment.assignmentapp.data.local.prefs.PrefHelper
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by Ujjwal on 18/01/18.
 */
@Module
abstract class AppModule {

    @Binds
    internal abstract fun provideContext(application: Application): Context

    @Binds
    abstract fun providePreferencesHelper(appPrefHelper: AppPrefHelper): PrefHelper

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideDeviceId(context: Context): String {
            return Settings.Secure.getString(context.contentResolver,
                    Settings.Secure.ANDROID_ID)
        }
    }
}