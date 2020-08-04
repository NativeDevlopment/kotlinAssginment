package com.coxassginment.di.module.application

import android.content.Context
import com.coxassginment.BaseApplication
import com.coxassginment.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class Interceptors {



@Provides
@Singleton
fun networkConnectionInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor = NetworkConnectionInterceptor(context)


}