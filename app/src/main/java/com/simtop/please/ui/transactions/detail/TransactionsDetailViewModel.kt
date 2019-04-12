package com.simtop.please.ui.transactions.detail

import androidx.lifecycle.ViewModel
import com.simtop.please.data.repository.PleaseRepository
import com.simtop.please.util.lazyDeferred

class TransactionsDetailViewModel(
    private val detailSku: String,
    private val pleaseRepository: PleaseRepository) : ViewModel() {

    val transactionsSearch by lazyDeferred {
        pleaseRepository.getTransactionsBySku(detailSku)
    }

}
