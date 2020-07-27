package com.coxassginment.data.network

import com.coxassginment.data.local.entity.GitUsers

sealed class DaoEntityMapper {
     data class GitUserListDao( val gitUserList:List<GitUsers>):DaoEntityMapper()
}