package com.saurabhpatel.apps.di

import android.app.Application
import com.saurabhpatel.apps.core.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class,
        ViewModelBindingModule::class))
interface ApplicationComponent {

    fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {
        // read : https://stackoverflow.com/questions/46197410/understanding-dagger-2-component-builder-annotation
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
