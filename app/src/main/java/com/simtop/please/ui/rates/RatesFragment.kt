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
import kotlinx.android.synthetic.main.rates_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RatesFragment : Fragment() {

    companion object {
        fun newInstance() = RatesFragment()
    }

    private lateinit var viewModel: RatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rates_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RatesViewModel::class.java)
        // TODO: Use the ViewModel

        val gnbService = GNBService(ConnectivityInterceptorImpl(this.context!!))
        val pleaseNetworkDataSource = PleaseNetworkDataSourceImpl(gnbService)

        pleaseNetworkDataSource.downloadedRates.observe(this, Observer {
            textViewRates.text = it.toString()

        })

        GlobalScope.launch(Dispatchers.Main) {
            pleaseNetworkDataSource.fetchRates()
        }


    }

}
