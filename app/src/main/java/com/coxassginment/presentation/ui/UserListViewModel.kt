package com.coxassginment.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.coxassginment.R
import com.coxassginment.BR
import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.local.entity.GitUsers
import com.coxassginment.data.network.GitRepository
import com.coxassginment.data.network.Resource
import com.coxassginment.data.network.ResultState
import com.coxassginment.presentation.ui.listner.OnItemClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

class UserListViewModel @Inject constructor(val gitRepository:GitRepository ,val gitUsersDao: GitUsersDao) :BaseViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    private val loadTrigger = MutableLiveData(Unit)

    val isError = MutableLiveData<Boolean>()
    var errorString = MutableLiveData<String>()
    var userList: MutableLiveData<List<GitUsers>> = MutableLiveData()
    //  var responseData: LiveData<Resource<List<GitUsers>>> = MutableLiveData()
    var id=MutableLiveData<Long>()
    val gitUserListBinding = ItemBinding.of<GitUsers> { todoListBinding, _, _ ->
        todoListBinding.set(BR.item, R.layout.item_user_list).
        bindExtra(BR.listener,object :OnItemClickListener<GitUsers>{
            override fun onItemClick(option: GitUsers) {
                id.postValue(option.id)
            }

        })

    }
    fun uiHandler(resourceData: Resource<List<GitUsers>>) {
        when(resourceData.status){
            Resource.Status.LOADING->{
                isLoading.postValue(true)
                isError.postValue(false)
            }
            Resource.Status.SUCCESS -> {
                if (!resourceData.data.isNullOrEmpty()) {


                    isLoading.postValue(false)
                    isError.postValue(false)
                    userList.postValue(resourceData.data)

                }
            }
            Resource.Status.ERROR->{
                isLoading.postValue(false)
                isError.postValue(true)
                errorString.postValue(resourceData.message)
            }
        }
    }
    fun  getUserListData() {
        isLoading.postValue(true)
        loadTrigger.value=Unit

    }
    val responseData: LiveData<Resource<List<GitUsers>>> = loadTrigger.switchMap {
        gitRepository.getLiveData()    }


}