package com.simtop.please.ui.rates

import androidx.lifecycle.ViewModel
import com.simtop.please.data.provider.UnitProvider
import com.simtop.please.data.repository.PleaseRepository
import com.simtop.please.util.lazyDeferred

class RatesViewModel (
    private val pleaseRepository: PleaseRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    val rates by lazyDeferred {
        pleaseRepository.getRates()
    }

    private val systemType = unitProvider.getUnitType()
    val isEur : String
        get() = systemType.name
}
