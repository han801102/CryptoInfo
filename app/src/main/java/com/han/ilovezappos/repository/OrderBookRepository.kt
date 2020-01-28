package com.han.ilovezappos.repository

import androidx.lifecycle.MutableLiveData
import com.han.ilovezappos.api.*
import com.han.ilovezappos.model.Order
import com.han.ilovezappos.model.OrderBook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderBookRepository {

    val api = ApiClient().getApi(OrderBookApi::class.java)

    fun getOrderBook(): MutableLiveData<OrderBook> {
        val orderBook = MutableLiveData<OrderBook>()

        api.getOrderBook().enqueue(object : Callback<OrderBookResponse> {
            override fun onFailure(
                call: Call<OrderBookResponse>,
                t: Throwable
            ) {
                // TODO: Handle api exception
            }

            override fun onResponse(
                call: Call<OrderBookResponse>,
                response: Response<OrderBookResponse>
            ) {
                val newOrderBook = OrderBook()
                response.body()?.let {
                    it.asks.map { ask ->
                        newOrderBook.asks.add(Order(ask[0], ask[1]))
                    }
                    it.bids.map { bid ->
                        newOrderBook.bids.add(Order(bid[0], bid[1]))
                    }
                }

                if (newOrderBook.asks.isNotEmpty()) {
                    newOrderBook.asks.add(0, OrderBook.Type.Asks.name)
                }

                if (newOrderBook.bids.isNotEmpty()) {
                    newOrderBook.bids.add(0, OrderBook.Type.Bids.name)
                }

                orderBook.value = newOrderBook
            }
        })


        return orderBook
    }
}