package com.simtop.please.ui.rates

import androidx.lifecycle.ViewModel
import com.simtop.please.data.repository.PleaseRepository
import com.simtop.please.util.lazyDeferred

class RatesViewModel (
    private val pleaseRepository: PleaseRepository
) : ViewModel() {

    val rates by lazyDeferred {
        pleaseRepository.getRates()
    }
}
