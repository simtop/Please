package com.simtop.please.data.network

import androidx.lifecycle.LiveData
import com.simtop.please.data.network.response.RatesResponse

interface PleaseNetworkDataSource {
    val downloadedRates : LiveData<List<RatesResponse>>

    suspend fun fetchRates()
}