package com.coxassginment.di.module.application

import com.coxassginment.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class Interceptors {



@Provides
@Singleton
fun networkConnectionInterceptor(): NetworkConnectionInterceptor = NetworkConnectionInterceptor(BaseApplication.applicationContext())


}