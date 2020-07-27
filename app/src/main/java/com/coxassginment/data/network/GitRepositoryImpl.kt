package com.coxassginment.data.network

import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.local.entity.map

class GitRepositoryImpl (val api: IGitApi , val gitUsersDao: GitUsersDao):BaseRepositoryImpl(),GitRepository {
    override suspend fun getUserList(): ResultState<CommonResponseDto.ResponseItemList> {
        return try {
            val response = api.getUsers()
            val mappedData=response.map()
            gitUsersDao.insertAll(mappedData.gitUserList)
            ResultState.Success(response)
        } catch (e: Exception) {
            handleErrorReturn(e) as ResultState<CommonResponseDto.ResponseItemList>
        }
    }
}