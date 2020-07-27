package com.coxassginment.di.module.application

import com.coxassginment.data.network.IGitApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class ApiModule {

    @Provides
    @Singleton
    fun provideGitApi(retrofit: Retrofit): IGitApi {
        return retrofit.create(IGitApi::class.java)
    }



}