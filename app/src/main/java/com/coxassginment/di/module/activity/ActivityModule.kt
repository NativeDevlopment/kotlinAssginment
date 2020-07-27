package com.coxassginment.di.module.activity


import com.coxassginment.presentation.ui.HomeActivity
import com.coxassginment.presentation.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity
    @ContributesAndroidInjector()
    abstract fun contributeHomeActivity(): HomeActivity

 }