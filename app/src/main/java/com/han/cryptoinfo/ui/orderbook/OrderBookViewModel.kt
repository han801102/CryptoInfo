package com.han.cryptoinfo.ui.orderbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.han.cryptoinfo.model.OrderBook
import com.han.cryptoinfo.repository.OrderBookRepository

class OrderBookViewModel : ViewModel() {
    val orderBook: LiveData<OrderBook> = OrderBookRepository().getOrderBook()
}
