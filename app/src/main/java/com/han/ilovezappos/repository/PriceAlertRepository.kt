package com.han.ilovezappos.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.han.ilovezappos.api.*
import com.han.ilovezappos.db.dao.PriceAlertDao
import com.han.ilovezappos.db.entity.PriceAlert
import com.han.ilovezappos.model.Order
import com.han.ilovezappos.model.OrderBook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriceAlertRepository(private val priceAlertDao: PriceAlertDao) {

    val allPriceAlert: LiveData<List<PriceAlert>> = priceAlertDao.getAllPriceAlert()

    suspend fun insert(priceAlert: PriceAlert) {
        priceAlertDao.insert(priceAlert)
    }

    suspend fun delete(priceAlert: PriceAlert) {
        priceAlertDao.delete(priceAlert)
    }
}