package com.simtop.please.util

import com.simtop.please.data.network.response.RatesResponse

object MoneyConverter{


    //Creates Table, allways you need to call it
    fun convertToEur(listResponse: List<RatesResponse>): String {

        val eur = "1"
        var usd = "0"
        var cad = "0"
        var aud = "0"
        var usdcad = "0"
        var usdaud = "0"
        var cadusd = "0"
        var cadaud = "0"
        var audusd = "0"
        var audcad = "0"

        listResponse.forEach {
            if (it.from == "EUR") {
                when {
                    it.to == "USD" -> usd = it.rate
                    it.to == "CAD" -> cad = it.rate
                    it.to == "AUD" -> aud = it.rate
                }
            }
            else {
                when {
                    it.from == "USD"  && it.to =="CAD" -> usdcad = it.rate
                    it.from == "USD"  && it.to =="AUD" -> usdaud = it.rate

                    it.from == "CAD"  && it.to =="USD" -> cadusd = it.rate
                    it.from == "CAD"  && it.to =="AUD" -> cadaud = it.rate

                    it.from == "AUD"  && it.to =="USD" -> audusd = it.rate
                    it.from == "AUD"  && it.to =="CAD" -> audcad = it.rate
                }
            }
        }

        if(usd == "0" && cad!= "0" && aud!="0"){
            if (cadusd!="0") usd = DecimalOperations.multiplyDecimalOperations(cad,cadusd)
            else if (audusd!="0") usd = DecimalOperations.multiplyDecimalOperations(aud,audusd)
        }

        else if(usd != "0" && cad == "0" && aud!="0"){
            if (usdcad!="0") cad = DecimalOperations.multiplyDecimalOperations(usd,usdcad)
            else if (audcad!="0") cad = DecimalOperations.multiplyDecimalOperations(aud,audcad)
        }
        else if(usd != "0" && cad != "0" && aud =="0"){
            if (usdaud!="0") aud = DecimalOperations.multiplyDecimalOperations(usd,usdaud)
            else if (cadaud!="0") aud = DecimalOperations.multiplyDecimalOperations(cad,cadaud)
        }
        else if(usd == "0" && cad == "0" && aud!="0"){
            if (audusd!="0") {
                usd = DecimalOperations.multiplyDecimalOperations(aud,audusd)
                cad = DecimalOperations.multiplyDecimalOperations(usd,usdcad)
            }
            else if (audcad!="0") {
                cad = DecimalOperations.multiplyDecimalOperations(aud,audcad)
                usd = DecimalOperations.multiplyDecimalOperations(cad,cadusd)
            }
        }
        else if(usd != "0" && cad == "0" && aud=="0"){
            if (usdcad!="0") {
                cad = DecimalOperations.multiplyDecimalOperations(usd,usdcad)
                aud = DecimalOperations.multiplyDecimalOperations(cad,cadaud)
            }
            else if (usdaud!="0") {
                aud = DecimalOperations.multiplyDecimalOperations(usd,audusd)
                cad = DecimalOperations.multiplyDecimalOperations(aud,audcad)
            }
        }
        else if(usd == "0" && cad != "0" && aud=="0"){
            if (cadusd!="0") {
                usd = DecimalOperations.multiplyDecimalOperations(cad,cadusd)
                aud = DecimalOperations.multiplyDecimalOperations(usd,usdaud)
            }
            else if (cadaud!="0") {
                aud = DecimalOperations.multiplyDecimalOperations(cad,cadaud)
                usd = DecimalOperations.multiplyDecimalOperations(aud,audusd)
            }
        }

        return "EUR + $eur USD = $usd + CAD + $cad + AUD + $aud"
    }
}