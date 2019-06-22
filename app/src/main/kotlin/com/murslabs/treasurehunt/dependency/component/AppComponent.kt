package com.murslabs.treasurehunt.dependency.component

import android.app.Application
import com.murslabs.treasurehunt.HuntApplication
import com.murslabs.treasurehunt.dependency.builder.ActivityBuilder
import com.murslabs.treasurehunt.dependency.builder.ServiceBuilder
import com.murslabs.treasurehunt.dependency.module.AppModule
import com.murslabs.treasurehunt.dependency.module.DbModule
import com.murslabs.treasurehunt.dependency.module.NetModule
import com.murslabs.treasurehunt.dependency.module.SystemServiceModule
import com.murslabs.treasurehunt.dependency.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (NetModule::class),
    (DbModule::class), (ActivityBuilder::class), (ServiceBuilder::class), (SystemServiceModule::class)])
interface AppComponent {

    fun inject(app: HuntApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}