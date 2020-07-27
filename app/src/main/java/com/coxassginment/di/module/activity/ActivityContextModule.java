package com.coxassginment.di.module.activity;

import android.app.Activity;
import android.content.Context;

import com.coxassginment.di.qualifier.ActivityContext;
import com.coxassginment.di.qualifier.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityContextModule {

    private final Context context;

    ActivityContextModule(Activity context) {
        this.context = context;
    }

    @ActivityContext
    @PerActivity
    @Provides
    public Context context() {
        return context;
    }
}
