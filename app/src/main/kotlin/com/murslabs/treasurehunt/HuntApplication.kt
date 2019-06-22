package com.murslabs.treasurehunt

import android.app.Activity
import android.app.Application
import android.app.Service
import com.akaita.java.rxjava2debug.RxJava2Debug
import com.murslabs.treasurehunt.dependency.utils.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject

class HuntApplication : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceAndroidInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)

        RxJavaPlugins.setErrorHandler(Timber::e)
        RxJava2Debug.enableRxJava2AssemblyTracking(PACKAGES)
        enableCrashlytics()
    }

    /**
     * This method just replicates Crashlytics' behaviour:
     *
     * It sets up the same system that Crashlytics does, but instead of sending the report to Fabric it will print it to LogCat
     */
    private fun enableCrashlytics() {
        val defaultUncaughtHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Timber.e(throwable)
            defaultUncaughtHandler.uncaughtException(thread, throwable)
        }
    }

    companion object {

        /**
         * This array assumes that the code I wrote for this app is contained under three root packages
         *
         * This specific example only needs "com.akaita.java.rxjava2debug.exampleandroid". I just added two more in order to show how you could do it
         */
        val PACKAGES = arrayOf(
                "com.mindtickle.android_app"
        )
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceAndroidInjector
    }

}