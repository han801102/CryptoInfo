package com.han.ilovezappos.repository

import androidx.lifecycle.MutableLiveData
import com.han.ilovezappos.api.ApiClient
import com.han.ilovezappos.api.CryptoPriceHistoryApi
import com.han.ilovezappos.api.CryptoHistoryResponse
import com.han.ilovezappos.model.Crypto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoHistoryRepository {

    val api = ApiClient().getApi(CryptoPriceHistoryApi::class.java)

    fun getPriceHistory(): MutableLiveData<List<Crypto>> {
        val priceHistory = MutableLiveData<List<Crypto>>()

        api.getPriceHistory().enqueue(object : Callback<List<CryptoHistoryResponse>> {
            override fun onFailure(
                call: Call<List<CryptoHistoryResponse>>,
                t: Throwable
            ) {
                // TODO: Handle api exception
            }

            override fun onResponse(
                call: Call<List<CryptoHistoryResponse>>,
                response: Response<List<CryptoHistoryResponse>>
            ) {
                val data = response.body()?.map { Crypto(it.timestamp, it.price) }
                    ?.sortedBy { it.timestamp }
                priceHistory.value = data?.distinctBy { it.timestamp }
            }
        })


        return priceHistory
    }
}