package com.han.cryptoinfo.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_alert")
data class PriceAlert(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "price")
    val price: Double
)