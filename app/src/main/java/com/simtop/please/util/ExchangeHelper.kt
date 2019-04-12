package com.simtop.please.util

import com.simtop.please.data.network.response.TransactionsResponse

object ExchangeHelper {

    //eurTable order of elements "EUR + $eur USD = $usd + CAD + $cad + AUD + $aud"
    fun moneyTypeFinder(type : String, transaction: TransactionsResponse, eurTable : Array<String>) : String {

        return convertionSelecter(type ,transaction,eurTable)
    }


    fun convertionSelecter(type : String, transaction : TransactionsResponse, eurTable: Array<String>) : String{

        var result = ""

        when{
            type == "EUR" && transaction.currency == "EUR" ->
                result = convertSameToSame(transaction.amount, eurTable)
            type == "EUR" && transaction.currency == "USD" ->
                result = convertUsdToEur(transaction.amount,eurTable)
            type == "EUR" && transaction.currency == "CAD" ->
                result = convertCadToEur(transaction.amount,eurTable)
            type == "EUR" && transaction.currency == "AUD" ->
                result = convertAudToEur(transaction.amount,eurTable)

            type == "USD" && transaction.currency == "USD" ->
                result = convertSameToSame(transaction.amount, eurTable)
            type == "USD" && transaction.currency == "EUR" ->
                result = convertEurToUsd(transaction.amount,eurTable)
            type == "USD" && transaction.currency == "CAD" ->
                result = convertEurToUsd(convertCadToEur(transaction.amount,eurTable),eurTable)
            type == "USD" && transaction.currency == "AUD" ->
                result = convertEurToUsd(convertAudToEur(transaction.amount,eurTable),eurTable)

            type == "CAD" && transaction.currency == "CAD" ->
                result = convertSameToSame(transaction.amount, eurTable)
            type == "CAD" && transaction.currency == "EUR" ->
                result = convertEurToCad(transaction.amount,eurTable)
            type == "CAD" && transaction.currency == "USD" ->
                result = convertEurToCad(convertUsdToEur(transaction.amount,eurTable),eurTable)
            type == "CAD" && transaction.currency == "AUD" ->
                result = convertEurToCad(convertAudToEur(transaction.amount,eurTable),eurTable)

            type == "AUD" && transaction.currency == "AUD" ->
                result = convertSameToSame(transaction.amount, eurTable)
            type == "AUD" && transaction.currency == "EUR" ->
                result = convertEurToAud(transaction.amount,eurTable)
            type == "AUD" && transaction.currency == "CAD" ->
                result = convertEurToAud(convertCadToEur(transaction.amount,eurTable),eurTable)
            type == "AUD" && transaction.currency == "USD" ->
                result = convertEurToAud(convertUsdToEur(transaction.amount,eurTable),eurTable)
        }

        return result
    }

    fun convertSameToSame(money : String, eurTable: Array<String>) : String{


        return money
    }


    fun convertUsdToEur(money : String, eurTable: Array<String>) : String{

        return DecimalOperations.divideDecimalOperations(money,eurTable[1])
    }

    fun convertCadToEur(money : String, eurTable: Array<String>) : String{

        return DecimalOperations.divideDecimalOperations(money,eurTable[2])
    }

    fun convertAudToEur(money : String, eurTable: Array<String>) : String{

        return DecimalOperations.divideDecimalOperations(money,eurTable[3])
    }

    fun convertEurToUsd(money : String, eurTable: Array<String>) : String{

        return DecimalOperations.multiplyDecimalOperations(money,eurTable[1])
    }

    fun convertEurToCad(money : String, eurTable: Array<String>) : String{

        return DecimalOperations.multiplyDecimalOperations(money,eurTable[2])
    }

    fun convertEurToAud(money : String, eurTable: Array<String>) : String{

        return DecimalOperations.multiplyDecimalOperations(money,eurTable[3])
    }

}