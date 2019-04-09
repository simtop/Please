package com.simtop.please.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.simtop.please.data.response.RatesResponse
import com.simtop.please.data.response.TransactionsResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GNBService {

    //Nothing to Querry
    @GET("rates.json")
    fun getRates(): Deferred<List<RatesResponse>>

    @GET("transactions.json")
    fun getTransactions() : Deferred<List<TransactionsResponse>>

    companion object {
        operator fun invoke(): GNBService {
            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://quiet-stone-2094.herokuapp.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GNBService::class.java)
        }
    }
}