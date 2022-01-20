package com.example.mpaywalletapp.data.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {
        private const val API_LINK = "https://mpay-android-interview.herokuapp.com/android/interview/"

        fun <T> getRetrofitInstance(api : Class<T>) : T {
           return Retrofit.Builder()
               .baseUrl(API_LINK)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(api)
        }
    }
}