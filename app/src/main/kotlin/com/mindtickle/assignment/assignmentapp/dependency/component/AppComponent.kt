package com.mindtickle.assignment.assignmentapp.dependency.component

import android.app.Application
import com.mindtickle.assignment.assignmentapp.MTApplication
import com.mindtickle.assignment.assignmentapp.dependency.builder.ActivityBuilder
import com.mindtickle.assignment.assignmentapp.dependency.builder.ServiceBuilder
import com.mindtickle.assignment.assignmentapp.dependency.module.AppModule
import com.mindtickle.assignment.assignmentapp.dependency.module.DbModule
import com.mindtickle.assignment.assignmentapp.dependency.module.NetModule
import com.mindtickle.assignment.assignmentapp.dependency.module.SystemServiceModule
import com.mindtickle.assignment.assignmentapp.dependency.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (NetModule::class),
    (DbModule::class), (ActivityBuilder::class), (ServiceBuilder::class), (SystemServiceModule::class)])
interface AppComponent {

    fun inject(app: MTApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}