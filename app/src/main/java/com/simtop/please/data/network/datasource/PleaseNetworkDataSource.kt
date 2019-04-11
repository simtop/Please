package com.simtop.please.data.network.datasource

import androidx.lifecycle.LiveData
import com.simtop.please.data.network.response.RatesResponse
import com.simtop.please.data.network.response.TransactionsResponse

interface PleaseNetworkDataSource {
    val downloadedRates : LiveData<List<RatesResponse>>
    val downloadedTransactions : LiveData<List<TransactionsResponse>>

    suspend fun fetchRates()
    suspend fun fetchTransactions()
}