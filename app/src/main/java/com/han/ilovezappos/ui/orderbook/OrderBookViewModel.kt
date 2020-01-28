package com.han.ilovezappos.ui.orderbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.han.ilovezappos.model.OrderBook
import com.han.ilovezappos.repository.OrderBookRepository

class OrderBookViewModel : ViewModel() {
    val orderBook: LiveData<OrderBook> = OrderBookRepository().getOrderBook()
}
