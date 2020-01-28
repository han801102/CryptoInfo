package com.han.ilovezappos.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.han.ilovezappos.model.Crypto
import com.han.ilovezappos.repository.CryptoHistoryRepository

class TransactionHistoryViewModel : ViewModel() {

    val cryptoPrices: LiveData<List<Crypto>> = CryptoHistoryRepository().getPriceHistory()

}
