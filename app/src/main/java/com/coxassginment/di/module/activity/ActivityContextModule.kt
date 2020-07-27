package com.coxassginment.di.module.activity

import android.content.Context
import com.coxassginment.di.qualifier.ActivityContext
import com.coxassginment.di.qualifier.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityContextModule(context: Context) {
    private var context: Context=context



    @ActivityContext
    @PerActivity
    @Provides
    fun context(): Context? {
        return context
    }
}