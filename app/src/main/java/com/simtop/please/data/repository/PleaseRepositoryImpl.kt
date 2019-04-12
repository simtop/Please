package com.simtop.please.data.repository

import androidx.lifecycle.LiveData
import com.simtop.please.data.db.RatesDao
import com.simtop.please.data.db.TransactionsDao
import com.simtop.please.data.network.datasource.PleaseNetworkDataSource
import com.simtop.please.data.network.response.RatesResponse
import com.simtop.please.data.network.response.TransactionsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class PleaseRepositoryImpl(
    private val ratesDao: RatesDao,
    private val transactionsDao: TransactionsDao,
    private val pleaseNetworkDataSource: PleaseNetworkDataSource
) : PleaseRepository {

    init {
        pleaseNetworkDataSource.apply {
            downloadedRates.observeForever { newListRatesResponse ->
                persistFetchedRatesResponse(newListRatesResponse)
            }
            downloadedTransactions.observeForever { newListTransactionsResponse ->
                persistFetchedTransactionsResponse(newListTransactionsResponse)
            }
        }
    }

    private fun persistFetchedRatesResponse(fetchedListRates : List<RatesResponse>){
        GlobalScope.launch(Dispatchers.IO) {
            ratesDao.deleteOldEntries()
            ratesDao.insert(fetchedListRates)
        }
    }

    private fun persistFetchedTransactionsResponse(fetchedListTransactions : List<TransactionsResponse>){
        GlobalScope.launch(Dispatchers.IO) {
            transactionsDao.deleteOldEntries()
            transactionsDao.insert(fetchedListTransactions)
        }
    }

    override suspend fun getRates(): LiveData<out List<RatesResponse>> {
        return withContext(Dispatchers.IO){
            initPleaseData()
            return@withContext ratesDao.getListRates()
        }
    }

    override suspend fun getTransactions(): LiveData<out List<TransactionsResponse>> {
        return withContext(Dispatchers.IO){
            initPleaseData()
            return@withContext transactionsDao.getListTransactions()
        }
    }
    override suspend fun getTransactionsBySku(detailSku : String): LiveData<out List<TransactionsResponse>> {
        return withContext(Dispatchers.IO){
            initPleaseData()
            return@withContext transactionsDao.getListTransactionsBySku(detailSku)
        }
    }

    private suspend fun initPleaseData(){
        //TODO: look for dummytime need
        val dummyTime = ZonedDateTime.now() //.minusHours(1)
        if(isFetchingTodayNeeded(dummyTime)) {
            fetchRates()
            fetchTransactions()
        }
    }

    private  suspend fun fetchRates(){
        pleaseNetworkDataSource.fetchRates()
    }

    private  suspend fun fetchTransactions(){
        pleaseNetworkDataSource.fetchTransactions()
    }

    private fun isFetchingTodayNeeded(lastFetchTime : ZonedDateTime) : Boolean{
        val halfHourAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(halfHourAgo)
    }
}