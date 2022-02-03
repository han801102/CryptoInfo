package com.han.cryptoinfo.ui.pricealert

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.han.cryptoinfo.db.CryptoInfoDatabase
import com.han.cryptoinfo.db.entity.PriceAlert
import com.han.cryptoinfo.repository.PriceAlertRepository
import kotlinx.coroutines.launch

class PriceAlertViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PriceAlertRepository

    val allPriceAlert: LiveData<List<PriceAlert>>

    init {
        val priceAlertDao = CryptoInfoDatabase.getDB(application).priceAlertDao()
        repository = PriceAlertRepository(priceAlertDao)
        allPriceAlert = repository.allPriceAlert
    }

    fun insert(priceAlert: PriceAlert) = viewModelScope.launch {
        repository.insert(priceAlert)
    }
}
