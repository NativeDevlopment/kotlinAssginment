package com.coxassginment.data.network

import androidx.lifecycle.LiveData
import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.local.entity.GitUsers
import com.coxassginment.data.local.entity.map

class GitRepositoryImpl (val api: IGitApi , val gitUsersDao: GitUsersDao):BaseRepositoryImpl(),GitRepository {

    private  fun getCharacters() = performGetOperation(
        databaseQuery = { gitUsersDao.select() },
        networkCall = {getUserList() },saveCallResult = {gitUsersDao.insertAll(it.map().gitUserList)}
    )
    override suspend fun getUserList(): ResultState<CommonResponseDto.ResponseItemList> {
        return try {
            val response = api.getUsers()
            ResultState.Success(response)
        } catch (e: Exception) {
            handleErrorReturn(e) as ResultState<CommonResponseDto.ResponseItemList>
        }
    }

    override  fun getLiveData(): LiveData<Resource<List<GitUsers>>> {
        return getCharacters()
    }
}