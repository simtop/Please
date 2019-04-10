package com.simtop.please.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simtop.please.data.network.response.RatesResponse
import com.simtop.please.util.NoConnectivityExeption

class PleaseNetworkDataSourceImpl
    (private val gnbService: GNBService) : PleaseNetworkDataSource {

    private val _downloadedRates = MutableLiveData<List<RatesResponse>>()
    override val downloadedRates: LiveData<List<RatesResponse>>
        get() = _downloadedRates

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
}
