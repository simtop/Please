package com.simtop.please.ui.rates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simtop.please.data.provider.UnitProvider
import com.simtop.please.data.repository.PleaseRepository

class RatesViewModelFactory(
    private val pleaseRepository: PleaseRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RatesViewModel(pleaseRepository, unitProvider) as T
    }
}