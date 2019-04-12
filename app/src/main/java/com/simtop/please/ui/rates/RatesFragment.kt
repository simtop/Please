package com.simtop.please.ui.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.simtop.please.R
import com.simtop.please.data.network.response.RatesResponse
import com.simtop.please.ui.base.ScopedFragment
import com.simtop.please.util.ExchangeHelper
import com.simtop.please.util.MoneyConverter
import kotlinx.android.synthetic.main.rates_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class RatesFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory : RatesViewModelFactory by instance()

    private lateinit var viewModel: RatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rates_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RatesViewModel::class.java)

        bindUI()

    }

    private fun bindUI() = launch{
        val ratesList = viewModel.rates.await()
        ratesList.observe(this@RatesFragment, Observer {
            if(it == null) return@Observer

            populateTableEur(it)
            updateAppName()
            updateFragmentName()
        })
    }

    private fun populateTableEur(it: List<RatesResponse>) {
        text_type.text = viewModel.isEur
        val eurTable: Array<String> = MoneyConverter.convertToEur(it)

        if (viewModel.isEur == "EUR") {
            first_value.text = eurTable[1]
            second_value.text = eurTable[2]
            third_value.text = eurTable[3]
        }
        else if (viewModel.isEur == "USD") {
            first_row_name.text = "EUR"
            first_value.text = ExchangeHelper.convertUsdToEur(eurTable[0],eurTable)
            second_value.text = ExchangeHelper.convertUsdToCad(eurTable[0],eurTable)
            third_value.text = ExchangeHelper.convertUsdToAud(eurTable[0],eurTable)
        }
        else if (viewModel.isEur == "CAD") {
            second_row_name.text = "EUR"
            first_value.text =  ExchangeHelper.convertCadToUsd(eurTable[0],eurTable)
            second_value.text = ExchangeHelper.convertCadToEur(eurTable[0],eurTable)
            third_value.text = ExchangeHelper.convertCadToAud(eurTable[0],eurTable)
        }
        else if (viewModel.isEur == "AUD") {
            third_row_name.text = "EUR"
            first_value.text = ExchangeHelper.convertAudToUsd(eurTable[0],eurTable)
            second_value.text = ExchangeHelper.convertAudToCad(eurTable[0],eurTable)
            third_value.text = ExchangeHelper.convertAudToEur(eurTable[0],eurTable)
        }

    }

    private fun updateFragmentName() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Legendary Exchange Rates"
    }

    private fun updateAppName() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "P.L.E.A.S.E."
    }
}
