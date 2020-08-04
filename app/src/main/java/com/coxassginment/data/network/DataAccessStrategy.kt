package com.coxassginment.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> ResultState<A>, saveCallResult: suspend (A) -> Unit
                               ): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map {Resource.success(it) }
        emitSource(source)

     //   networkCall.invoke()

        when (val response = networkCall.invoke()) {
            is ResultState.Loading -> {
            }
            is ResultState.Success -> {

                saveCallResult(response.data)

            }
            is ResultState.Error -> {
                response.error?.let {
                    emit(Resource.error(it.errors[0].errorMessage))
                    emitSource(source)

                }
            }

        }

    }