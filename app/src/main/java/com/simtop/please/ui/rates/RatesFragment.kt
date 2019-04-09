package com.simtop.please.ui.rates

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.simtop.please.R
import com.simtop.please.data.GNBService
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

        val gnbService = GNBService()
        GlobalScope.launch(Dispatchers.Main) {
            val ratesResponse = gnbService.getRates().await()
            textViewRates.text = ratesResponse.toString()
        }


    }

}
