package com.simtop.please.util

import java.math.BigDecimal

object DecimalOperations {
    fun sumDecimalOperations (zero : String = "0", number : String) : String{
        var round = BigDecimal(zero).setScale(2, BigDecimal.ROUND_HALF_EVEN)
        val numberBigDecimal = BigDecimal(number).setScale(2,BigDecimal.ROUND_HALF_EVEN)
        val result  = (round + numberBigDecimal).setScale(2,BigDecimal.ROUND_HALF_EVEN)
        return result.toString()
    }

    fun multiplyDecimalOperations(amount: String, rate: String): String {
        val amountBigDecimal = BigDecimal(amount).setScale(2,BigDecimal.ROUND_HALF_EVEN)
        val rateBigDecimal = BigDecimal(rate).setScale(2,BigDecimal.ROUND_HALF_EVEN)
        val result = (amountBigDecimal.multiply(rateBigDecimal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)

        return result.toString()
    }
}