package com.saurabhpatel.apps.di

import com.saurabhpatel.apps.photos.ui.PhotosActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    abstract fun bindPhotosActivity(): PhotosActivity
}
