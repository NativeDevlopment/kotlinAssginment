package com.coxassginment.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.local.entity.GitUsers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(private val usersDao: GitUsersDao) :BaseViewModel() {
    var users=MutableLiveData<GitUsers>()
    fun getUserData(long: Long?) {
        long?.let {
            viewModelScope.launch (Dispatchers.IO){
                users.postValue(usersDao.findById(it))

            }

        }
    }
}