package com.simtop.please.data.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simtop.please.data.network.GNBService
import com.simtop.please.data.network.response.RatesResponse
import com.simtop.please.data.network.response.TransactionsResponse
import com.simtop.please.util.NoConnectivityExeption

class PleaseNetworkDataSourceImpl(
    private val gnbService: GNBService) : PleaseNetworkDataSource {

    private val _downloadedRates = MutableLiveData<List<RatesResponse>>()
    override val downloadedRates: LiveData<List<RatesResponse>>
        get() = _downloadedRates


    private val _downloadedTransactions = MutableLiveData<List<TransactionsResponse>>()
    override val downloadedTransactions: LiveData<List<TransactionsResponse>>
        get() = _downloadedTransactions

    override suspend fun fetchRates() {
        try {
            val fetchedRates = gnbService
                .getRates()
                .await()
            //TODO:Mirar fetchedRates
            _downloadedRates.postValue(fetchedRates)
        }
        catch (e: NoConnectivityExeption) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchTransactions() {
        try {
            val fetchedTransactions = gnbService
                .getTransactions()
                .await()
            _downloadedTransactions.postValue(fetchedTransactions)
        }
        catch (e: NoConnectivityExeption) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}
