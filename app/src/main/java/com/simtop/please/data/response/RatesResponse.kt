package com.simtop.please.data.response

import java.math.BigDecimal

data class RatesResponse(

    val from: String,
    val to: String,
    val rate: String
)