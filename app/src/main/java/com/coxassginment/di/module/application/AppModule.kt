package com.coxassginment.di.module.application

import android.content.Context
import com.coxassginment.BaseApplication
import com.coxassginment.di.qualifier.ApplicationContext

import dagger.Binds
import dagger.Module

@Module(includes = [OkHttpClientModule::class])
abstract class AppModule() {

    @ApplicationContext
    @Binds
    abstract fun provideApplicationContext(myApplication: BaseApplication): Context

}