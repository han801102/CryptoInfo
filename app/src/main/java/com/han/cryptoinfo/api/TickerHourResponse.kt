package com.han.cryptoinfo.api

import com.google.gson.annotations.SerializedName

class TickerHourResponse {

    @SerializedName("last")
    var lastPrice: String = ""

}
