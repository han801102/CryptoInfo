package com.han.ilovezappos.ui.pricealert

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.han.ilovezappos.db.ILoveZapposDatabase
import com.han.ilovezappos.db.entity.PriceAlert
import com.han.ilovezappos.repository.PriceAlertRepository
import kotlinx.coroutines.launch

class PriceAlertViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PriceAlertRepository

    val allPriceAlert: LiveData<List<PriceAlert>>

    init {
        val priceAlertDao = ILoveZapposDatabase.getDB(application).priceAlertDao()
        repository = PriceAlertRepository(priceAlertDao)
        allPriceAlert = repository.allPriceAlert
    }

    fun insert(priceAlert: PriceAlert) = viewModelScope.launch {
        repository.insert(priceAlert)
    }
}
