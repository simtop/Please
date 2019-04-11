package com.simtop.please.ui.transactions.list

import androidx.lifecycle.ViewModel
import com.simtop.please.data.repository.PleaseRepository
import com.simtop.please.util.lazyDeferred

class TransactionsListViewModel (
    private val pleaseRepository: PleaseRepository
) : ViewModel() {

    val transactions by lazyDeferred {
        pleaseRepository.getTransactions()
    }
}
