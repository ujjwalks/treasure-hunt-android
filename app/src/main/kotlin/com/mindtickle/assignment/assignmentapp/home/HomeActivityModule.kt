package com.mindtickle.assignment.assignmentapp.home

import android.content.Context
import com.mindtickle.assignment.assignmentapp.dependency.module.BaseActivityModule
import com.mindtickle.assignment.assignmentapp.dependency.qualifiers.ActivityContext
import com.mindtickle.assignment.assignmentapp.dependency.scope.PerActivity
import dagger.Binds
import dagger.Module

@Module
abstract class HomeActivityModule : BaseActivityModule() {

    @Binds
    @PerActivity
    @ActivityContext
    abstract fun provideActivityContext(homeActivity: HomeActivity): Context
}
