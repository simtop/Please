package com.simtop.please.ui.rates

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.simtop.please.R
import com.simtop.please.data.network.ConnectivityInterceptorImpl
import com.simtop.please.data.network.GNBService
import com.simtop.please.data.network.PleaseNetworkDataSourceImpl
import com.simtop.please.ui.base.ScopedFragment
import com.simtop.please.util.MoneyConverter
import kotlinx.android.synthetic.main.rates_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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
        val todaysWeather = viewModel.rates.await()
        todaysWeather.observe(this@RatesFragment, Observer {
            if(it == null) return@Observer
            textViewRates.text = it.toString() + "\n" + MoneyConverter.convertToEur(it)

        })
    }

}
