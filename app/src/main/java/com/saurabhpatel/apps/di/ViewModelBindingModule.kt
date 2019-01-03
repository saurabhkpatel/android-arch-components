package com.saurabhpatel.apps.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saurabhpatel.apps.photos.viewmodel.PhotosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress
@Module
abstract class ViewModelBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(PhotosViewModel::class)
    abstract fun bindPhotosViewModel(photosViewModel: PhotosViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory
}
