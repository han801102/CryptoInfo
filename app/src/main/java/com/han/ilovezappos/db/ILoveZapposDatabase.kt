package com.han.ilovezappos.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.han.ilovezappos.db.dao.PriceAlertDao
import com.han.ilovezappos.db.entity.PriceAlert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(PriceAlert::class), version = 1)
abstract class ILoveZapposDatabase : RoomDatabase() {
    abstract fun priceAlertDao(): PriceAlertDao

//    private class ILoveZapposDatabaseCallback(
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
        private var instance: ILoveZapposDatabase? = null

        fun getDB(context: Context): ILoveZapposDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    ILoveZapposDatabase::class.java,
                    "i_love_zappos_db"
                ).build()
                instance = db
                return db
            }
        }
    }
}