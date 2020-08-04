package com.coxassginment.di.module.application

import android.content.Context
import androidx.room.Room
import com.coxassginment.BaseApplication
import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.local.database.AppDb
import com.coxassginment.di.qualifier.ApplicationContext

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DbModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(@ApplicationContext context: Context): AppDb {
        return Room.databaseBuilder(context, AppDb::class.java, "assignment.db").build()
    }

    @Provides
    @Singleton
    internal fun provideGitUsersDao(appDb: AppDb): GitUsersDao {
        return appDb.gitUsersDao()
    }
}