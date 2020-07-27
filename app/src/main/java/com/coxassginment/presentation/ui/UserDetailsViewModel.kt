package com.coxassginment.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.local.entity.GitUsers
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(val usersDao: GitUsersDao) :BaseViewModel() {
    var users=MutableLiveData<GitUsers>()
    fun getUserData(long: Long?) {
        long?.apply {
            users.postValue(usersDao.findById(this))

        }
    }
}