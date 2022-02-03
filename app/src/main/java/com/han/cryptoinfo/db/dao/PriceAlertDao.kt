package com.han.cryptoinfo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.han.cryptoinfo.db.entity.PriceAlert

@Dao
interface PriceAlertDao {

    @Query("SELECT * FROM price_alert")
    fun getAllPriceAlert(): LiveData<List<PriceAlert>>

    @Query("SELECT * FROM price_alert")
    fun getAllPriceAlertSync(): List<PriceAlert>

    @Insert
    suspend fun insert(vararg priceAlert: PriceAlert)

    @Delete
    suspend fun delete(priceAlert: PriceAlert)
}