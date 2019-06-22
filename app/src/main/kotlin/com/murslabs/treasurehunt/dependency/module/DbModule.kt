package com.murslabs.treasurehunt.dependency.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.murslabs.android.data.db.MTDatabase
import com.murslabs.treasurehunt.dependency.scope.PerApplication
import com.murslabs.treasurehunt.utils.AppConstants
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    @PerApplication
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(AppConstants.PREF_NAME, MODE_PRIVATE)
    }

    @Provides
    @PerApplication
    fun provideRxSharedPreferences(prefs: SharedPreferences): RxSharedPreferences {
        return RxSharedPreferences.create(prefs)
    }

    @Provides
    @PerApplication
    fun getMTDatabase(application: Application): MTDatabase {
        return Room.databaseBuilder(application, MTDatabase::class.java, "mt_db").build()
    }
}
