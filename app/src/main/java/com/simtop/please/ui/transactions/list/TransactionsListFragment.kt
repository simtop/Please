package com.simtop.please.ui.transactions.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.simtop.please.R
import com.simtop.please.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.transactions_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class TransactionsListFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory : TransactionsListViewModelFactory by instance()

    private lateinit var viewModel: TransactionsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transactions_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TransactionsListViewModel::class.java)

        bindUI()

    }
    private fun bindUI() = launch{
        val transactionsList = viewModel.transactions.await()
        transactionsList.observe(this@TransactionsListFragment, Observer {
            if(it == null) return@Observer
            textViewTransactions.text = it.toString()

        })
    }
}