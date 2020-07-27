package com.coxassginment.data.network

interface GitRepository :BaseRepository {
   suspend fun getUserList():ResultState<CommonResponseDto.ResponseItemList>
}