package com.simtop.please.data.repository

import androidx.lifecycle.LiveData
import com.simtop.please.data.db.RatesDao
import com.simtop.please.data.network.PleaseNetworkDataSource
import com.simtop.please.data.network.response.RatesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class PleaseRepositoryImpl(
    private val ratesDao: RatesDao,
    private val pleaseNetworkDataSource: PleaseNetworkDataSource
) : PleaseRepository {

    init {
        pleaseNetworkDataSource.downloadedRates.observeForever {newListRatesResponse->
            persistFetchedRatesResponse(newListRatesResponse)
        }
    }

    private fun persistFetchedRatesResponse(fetchedListRates : List<RatesResponse>){
        GlobalScope.launch(Dispatchers.IO) {
            //TODO: CHECK FOR UPSTERT
            ratesDao.deleteOldEntries()
            ratesDao.insert(fetchedListRates)
        }
    }

    override suspend fun getRates(): LiveData<out List<RatesResponse>> {
        return withContext(Dispatchers.IO){
            initRatesData()
            return@withContext ratesDao.getListRates()
        }
    }

    private suspend fun initRatesData(){
        val dummyTime = ZonedDateTime.now().minusHours(1)
        if(isFetchingTodayNeeded(dummyTime))
            fetchRates()
    }

    private  suspend fun fetchRates(){
        pleaseNetworkDataSource.fetchRates()
    }

    private fun isFetchingTodayNeeded(lastFetchTime : ZonedDateTime) : Boolean{
        val halfHourAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(halfHourAgo)
    }
}