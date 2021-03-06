package com.simtop.please.ui.transactions.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simtop.please.data.provider.UnitProvider
import com.simtop.please.data.repository.PleaseRepository

class TransactionsDetailViewModelFactory(
    private val detailSku: String,
    private val pleaseRepository: PleaseRepository,
    private val unitProvider: UnitProvider) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionsDetailViewModel(detailSku, pleaseRepository, unitProvider) as T
    }
}