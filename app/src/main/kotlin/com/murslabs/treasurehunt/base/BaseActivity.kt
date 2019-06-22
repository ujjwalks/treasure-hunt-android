package com.murslabs.treasurehunt.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Ujjwal on 29/01/18.
 */
/**
 * Abstract base class for an activity
 * @viewmodel
 * @binding
 * binds view
 * initialize view model
 */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity(), HasSupportFragmentInjector {

    protected var binding: B? = null
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    /* Sets the content view, creates the binding and attaches the view to the view model */
    protected fun setAndBindContentView(@LayoutRes layoutResID: Int) {
        binding = DataBindingUtil.setContentView(this, layoutResID)
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}