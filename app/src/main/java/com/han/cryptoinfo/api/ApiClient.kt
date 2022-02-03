package com.han.cryptoinfo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    fun <T> getApi(api: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl("https://www.bitstamp.net/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(api)
    }
}