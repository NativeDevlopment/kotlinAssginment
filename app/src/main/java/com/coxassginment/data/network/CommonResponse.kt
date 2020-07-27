package com.coxassginment.data.network

data class CommonResponse<T>(val error: Boolean, val errorMessages: Any?, val data: T?)