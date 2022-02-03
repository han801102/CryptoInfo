package com.han.cryptoinfo.api

import com.google.gson.annotations.SerializedName

class CryptoHistoryResponse {

    @SerializedName("date")
    var timestamp: Int = 0

    @SerializedName("price")
    var price: Double = .0
}