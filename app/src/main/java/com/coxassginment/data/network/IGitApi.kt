package com.coxassginment.data.network

import retrofit2.http.GET

interface IGitApi {

    @GET("search/users?q=language:java+location:lagos")
    suspend  fun getUsers(): CommonResponseDto.ResponseItemList


}