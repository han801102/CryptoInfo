package com.han.ilovezappos.api

import retrofit2.Call
import retrofit2.http.GET

interface OrderBookApi {
    @GET("order_book/btcusd/")
    fun getOrderBook(): Call<OrderBookResponse>
}