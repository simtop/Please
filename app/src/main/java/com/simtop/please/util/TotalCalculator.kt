package com.simtop.please.util

import com.simtop.please.data.network.response.TransactionsResponse

object TotalCalculator {

    fun calculateTotal(type : String, listTransaction: List<TransactionsResponse>, eurTable : Array<String>) : String{

        var total = "0"

        listTransaction.forEach {
            total = DecimalOperations.sumDecimalOperations(total,(ExchangeHelper.moneyTypeFinder(type,it,eurTable)))
        }

        return total
    }
}