package com.han.cryptoinfo.repository

import androidx.lifecycle.LiveData
import com.han.cryptoinfo.db.dao.PriceAlertDao
import com.han.cryptoinfo.db.entity.PriceAlert

class PriceAlertRepository(private val priceAlertDao: PriceAlertDao) {

    val allPriceAlert: LiveData<List<PriceAlert>> = priceAlertDao.getAllPriceAlert()

    suspend fun insert(priceAlert: PriceAlert) {
        priceAlertDao.insert(priceAlert)
    }

    suspend fun delete(priceAlert: PriceAlert) {
        priceAlertDao.delete(priceAlert)
    }
}