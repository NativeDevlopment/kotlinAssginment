package com.coxassginment.di.module.application

import androidx.room.Room
import com.coxassginment.BaseApplication
import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.local.database.AppDb

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DbModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(): AppDb {
        return Room.databaseBuilder(BaseApplication.applicationContext(), AppDb::class.java, "assignment.db").allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideGitUsersDao(appDb: AppDb): GitUsersDao {
        return appDb.gitUsersDao()
    }
}