package com.coxassginment.di.module.application


import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class OkHttpClientModule {


    private val CONNECT_TIMEOUT: Long = 30
    private val CONNECT_TIME_UNIT = TimeUnit.SECONDS
    private val READ_TIMEOUT: Long = 30
    private val READ_TIME_UNIT = TimeUnit.SECONDS



    @Provides
    @Singleton
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor ,networkConnectionInterceptor: NetworkConnectionInterceptor ): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val okHttpClientBuilder : OkHttpClient.Builder = OkHttpClient()
            .newBuilder()
            .dispatcher(dispatcher)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(networkConnectionInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, CONNECT_TIME_UNIT)
            .readTimeout(READ_TIMEOUT, READ_TIME_UNIT)


        return okHttpClientBuilder.build()

    }



    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


}