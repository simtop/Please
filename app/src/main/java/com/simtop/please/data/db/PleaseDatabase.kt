package com.simtop.please.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.simtop.please.data.network.response.RatesResponse
import com.simtop.please.data.network.response.TransactionsResponse

@Database(entities = [RatesResponse::class, TransactionsResponse::class], version = 1)
abstract class PleaseDatabase : RoomDatabase(){

    abstract fun ratesDao() : RatesDao
    abstract fun transactionsDao() : TransactionsDao

    companion object {
        @Volatile private var instance: PleaseDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                PleaseDatabase::class.java, "please_database.db")
                .build()
    }
}