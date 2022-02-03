package com.han.cryptoinfo.api

import com.google.gson.annotations.SerializedName

class OrderBookResponse {


    @SerializedName("bids")
    var bids: List<List<String>> = ArrayList()

    @SerializedName("asks")
    var asks: List<List<String>> = ArrayList()
}



