package com.coxassginment.data.network

import androidx.lifecycle.LiveData
import com.coxassginment.data.local.entity.GitUsers

interface GitRepository :BaseRepository {
   suspend fun getUserList():ResultState<CommonResponseDto.ResponseItemList>
    fun getLiveData(): LiveData<Resource<List<GitUsers>>>
}