package com.simtop.please.data.repository

import androidx.lifecycle.LiveData
import com.simtop.please.data.network.response.RatesResponse

interface PleaseRepository {
    suspend fun getRates() : LiveData<out List<RatesResponse>>
}