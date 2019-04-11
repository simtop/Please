package com.simtop.please.ui.transactions.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simtop.please.data.repository.PleaseRepository

class TransactionsListViewModelFactory (
    private val pleaseRepository: PleaseRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionsListViewModel(pleaseRepository) as T
    }
}