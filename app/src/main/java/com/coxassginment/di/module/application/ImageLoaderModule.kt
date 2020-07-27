package com.coxassginment.di.module.application

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.coxassginment.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ImageLoaderModule {

    @Provides
    fun provideGlide(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
    }
}