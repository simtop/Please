package com.simtop.please.data.response

import java.math.BigDecimal

data class TransactionsResponse(
    val sku: String,
    val amount: String,
    val currency: String
)