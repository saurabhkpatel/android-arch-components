package com.saurabhpatel.apps.di

import android.app.Application
import android.content.Context
import com.saurabhpatel.apps.api.PhotoApiService
import com.saurabhpatel.apps.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Saurabh Patel on 4/4/18.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    internal fun provideApiService(): PhotoApiService {
        return Retrofit.Builder()
                .baseUrl(PhotoApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(PhotoApiService::class.java)
    }
}
