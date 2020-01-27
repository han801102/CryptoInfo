package com.han.ilovezappos.api

import retrofit2.Call
import retrofit2.http.GET

interface CryptoPriceHistoryApi {
    @GET("transactions/btcusd")
    fun getPriceHistory(): Call<List<CryptoHistoryResponse>>
}