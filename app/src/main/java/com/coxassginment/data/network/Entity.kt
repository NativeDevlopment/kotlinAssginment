package com.coxassginment.data.network

sealed class Entity {

    data class ErrorEntity(val errors: List<Error>) : Entity()

    data class Error(val errorCode: String, val errorMessage: String) : Entity()

    data class CommonResponse<T>(
        val error: Any?, val errorMessages: Any?, val data: T
    ) : Entity()
}