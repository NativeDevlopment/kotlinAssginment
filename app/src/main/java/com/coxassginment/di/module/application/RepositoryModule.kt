package com.coxassginment.di.module.application


import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.network.GitRepository
import com.coxassginment.data.network.GitRepositoryImpl
import com.coxassginment.data.network.IGitApi
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class RepositoryModule {

    @Provides
    fun provideGitRepository(api: IGitApi , gitUsersDao: GitUsersDao): GitRepository {
        return GitRepositoryImpl(api,gitUsersDao)
    }


}