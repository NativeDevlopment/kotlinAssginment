package com.coxassginment.presentation.ui

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coxassginment.R
import com.coxassginment.BR
import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.local.entity.GitUsers
import com.coxassginment.data.local.entity.map
import com.coxassginment.data.network.Entity
import com.coxassginment.data.network.GitRepository
import com.coxassginment.data.network.ResultState
import com.coxassginment.presentation.ui.listner.OnItemClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

class UserListViewModel @Inject constructor(val gitRepository:GitRepository ,val gitUsersDao: GitUsersDao) :BaseViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()
    var errorString = MutableLiveData<String>()
    var userList: MutableLiveData<List<GitUsers>> = MutableLiveData()
    var id=MutableLiveData<Long>()
    val gitUserListBinding = ItemBinding.of<GitUsers> { todoListBinding, _, _ ->
        todoListBinding.set(BR.item, R.layout.item_user_list).
        bindExtra(BR.listener,object :OnItemClickListener<GitUsers>{
            override fun onItemClick(option: GitUsers) {
                id.postValue(option.id)
            }

        })

    }
    fun  getUserListData(){
        isLoading.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            val resp=getLocalUserData()

            if(resp.isNotEmpty()){
                withContext(Dispatchers.Main){
                    isLoading.postValue(false)
                    isError.postValue(false)
                    userList.postValue(resp)
                }
            }else {
                when (val response = gitRepository.getUserList()) {
                    is ResultState.Loading -> {
                        withContext(Dispatchers.Main){
                            isLoading.value=true}
                    }
                    is ResultState.Success -> {
                        val  userData=getLocalUserData()
                        withContext(Dispatchers.Main) {
                            isLoading.postValue(false)
                            isError.postValue(false)
                            userList.postValue(userData)
                        }

                    }
                    is ResultState.Error -> {
                        withContext(Dispatchers.Main) {
                            isError.postValue(true)
                            isLoading.postValue( false)

                            response.error?.let {
                                errorString.postValue(it.errors[0].errorMessage)

                            }
                        }
                    }
                }
            }
        }
    }
    private  fun getLocalUserData():List<GitUsers> {
        Log.e("thread name",Thread.currentThread().name)
        return   gitUsersDao.select()
    }

}