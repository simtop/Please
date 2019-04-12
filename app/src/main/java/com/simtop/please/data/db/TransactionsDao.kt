package com.simtop.please.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simtop.please.data.network.response.TransactionsResponse

@Dao
interface TransactionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insert(transactionsList: List<TransactionsResponse>)

    @Query("SELECT * FROM transactions_list")
    fun getListTransactions(): LiveData<List<TransactionsResponse>>

    //TODO : check the query
    @Query("SELECT * FROM transactions_list WHERE sku = :detailSku")
    fun getListTransactionsBySku(detailSku : String): LiveData<List<TransactionsResponse>>

    @Query("DELETE FROM transactions_list")
    fun deleteOldEntries()
}