package com.coxassginment

import android.content.Context
import com.coxassginment.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication :DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerApplicationComponent.builder().create(this)
    }
    init {
        /**
         * Application Instance
         */
        instance = this

    }

    companion object {
        private lateinit var instance: BaseApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }

        fun getInstance(): BaseApplication {
            return instance
        }
    }
}