package com.simtop.please.data.repository

import androidx.lifecycle.LiveData
import com.simtop.please.data.network.response.RatesResponse
import com.simtop.please.data.network.response.TransactionsResponse

interface PleaseRepository {
    suspend fun getRates() : LiveData<out List<RatesResponse>>
    suspend fun getTransactions() : LiveData <out List<TransactionsResponse>>
    suspend fun getTransactionsBySku(findSku : String) : LiveData <out List<TransactionsResponse>>
}