
package com.coxassginment.di.module.application

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import java.net.UnknownHostException


class NetworkConnectionInterceptor(context: Context) : Interceptor {
    private val mContext: Context = context


    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isConnected()){
            throw UnknownHostException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected() : Boolean {
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCap : NetworkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)!!
            when {
                networkCap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCap.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val nwInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            nwInfo?.isConnected!!
        }
    }

}
