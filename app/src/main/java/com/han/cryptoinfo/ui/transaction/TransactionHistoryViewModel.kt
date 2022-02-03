package com.han.cryptoinfo.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.han.cryptoinfo.model.Crypto
import com.han.cryptoinfo.repository.CryptoHistoryRepository

class TransactionHistoryViewModel : ViewModel() {

    val cryptoPrices: LiveData<List<Crypto>> = CryptoHistoryRepository().getPriceHistory()

}
