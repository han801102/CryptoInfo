package com.han.cryptoinfo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.han.cryptoinfo.db.dao.PriceAlertDao
import com.han.cryptoinfo.db.entity.PriceAlert

@Database(entities = arrayOf(PriceAlert::class), version = 1)
abstract class CryptoInfoDatabase : RoomDatabase() {
    abstract fun priceAlertDao(): PriceAlertDao

//    private class CryptoInfoDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            instance?.let {
//                scope.launch {
//                    var priceAlertDao = it.priceAlertDao()
//
//
//                }
//            }
//        }
//
//    }

    companion object {

        @Volatile
        private var instance: CryptoInfoDatabase? = null

        fun getDB(context: Context): CryptoInfoDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoInfoDatabase::class.java,
                    "crypto_info_db"
                ).build()
                instance = db
                return db
            }
        }
    }
}