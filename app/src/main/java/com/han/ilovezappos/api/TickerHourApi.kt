package com.han.ilovezappos.api

import retrofit2.Call
import retrofit2.http.GET

interface TickerHourApi {
    @GET("ticker_hour/btcusd/")
    fun getTicker(): Call<TickerHourResponse>
}