package com.han.ilovezappos.model

data class OrderBook(
    var bids: ArrayList<Any> = ArrayList(),
    var asks: ArrayList<Any> = ArrayList(),
    var type: Int = Type.Asks.ordinal
) {
    enum class Type {
        Bids,
        Asks
    }
}
