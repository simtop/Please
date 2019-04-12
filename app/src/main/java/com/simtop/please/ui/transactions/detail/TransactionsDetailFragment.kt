package com.simtop.please.ui.transactions.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.simtop.please.R
import com.simtop.please.ui.base.ScopedFragment
import com.simtop.please.util.DetailSkuNotFoundException
import com.simtop.please.util.MoneyConverter
import com.simtop.please.util.TotalCalculator
import kotlinx.android.synthetic.main.transactions_detail_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory

class TransactionsDetailFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactoryInstanceFactory
            : ((String) ->TransactionsDetailViewModelFactory) by factory()

    private lateinit var viewModel: TransactionsDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transactions_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let { TransactionsDetailFragmentArgs.fromBundle(it) }
        val detailSku = safeArgs?.detailSku ?: throw DetailSkuNotFoundException()



        viewModel = ViewModelProviders.of(this, viewModelFactoryInstanceFactory(detailSku))
            .get(TransactionsDetailViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        val transactionsList = viewModel.transactionsSearch.await()

        val ratesList = viewModel.rates.await()

         var tableRates : Array<String> = arrayOf("","","","")

        ratesList.observe(this@TransactionsDetailFragment, Observer {
            if(it == null) return@Observer
            //textViewRates.text = it.toString() + "\n" + MoneyConverter.convertToEur(it)
            //textViewRates.text = it.toString()
            tableRates = MoneyConverter.convertToEur(it)
        })


        transactionsList.observe(this@TransactionsDetailFragment, Observer {
            if (it == null) return@Observer
            //textViewTransactions.text = it.subList(0,7).toString() + " \n" + "\n"
            val prueba = TotalCalculator.calculateTotal(viewModel.isEur, it,tableRates)
            textViewSko.text = it[0].sku
            textViewTotal.text = prueba
            textViewMoneyType.text = viewModel.isEur
        })
    }

}
