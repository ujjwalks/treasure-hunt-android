package com.murslabs.treasurehunt.splash

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.murslabs.treasurehunt.home.HomeActivity
import com.murslabs.treasurehunt.login.LoginActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import org.jetbrains.anko.intentFor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var splashViewModel: SplashViewModel

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        splashViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)
        splashViewModel
                .isUserLoggedIn
                .delay(500, TimeUnit.MILLISECONDS)
                .subscribe { isLoggedIn ->
                    if (isLoggedIn) gotoApp()
                    else gotoLoginFlow()
                    finish()
                }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onResume() {
        super.onResume()
        splashViewModel.refresh()
    }

    private fun gotoLoginFlow() {
        startActivity(intentFor<LoginActivity>())
    }

    private fun gotoApp() {
        startActivity(intentFor<HomeActivity>())
    }
}