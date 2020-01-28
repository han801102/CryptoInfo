package com.han.ilovezappos.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.han.ilovezappos.db.entity.PriceAlert

@Dao
interface PriceAlertDao {

    @Query("SELECT * FROM price_alert")
    fun getAllPriceAlert(): LiveData<List<PriceAlert>>

    @Insert
    suspend fun insert(vararg priceAlert: PriceAlert)

    @Delete
    suspend fun delete(priceAlert: PriceAlert)
}