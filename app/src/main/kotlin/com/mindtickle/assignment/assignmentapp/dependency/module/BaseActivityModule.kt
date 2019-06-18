package com.mindtickle.assignment.assignmentapp.dependency.module

import android.arch.lifecycle.ViewModelProvider
import com.mindtickle.assignment.assignmentapp.utils.factory.ViewModelFactory
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