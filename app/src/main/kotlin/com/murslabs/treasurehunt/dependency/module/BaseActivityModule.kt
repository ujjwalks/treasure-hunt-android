package com.murslabs.treasurehunt.dependency.module

import android.arch.lifecycle.ViewModelProvider
import com.murslabs.treasurehunt.utils.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by subham on 20/02/18.
 */
@Module
abstract class BaseActivityModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}