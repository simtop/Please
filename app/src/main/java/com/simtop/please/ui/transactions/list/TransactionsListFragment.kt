package com.simtop.please.ui.transactions.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.simtop.please.R
import com.simtop.please.data.GNBService
import kotlinx.android.synthetic.main.transactions_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TransactionsListFragment : Fragment() {

    companion object {
        fun newInstance() = TransactionsListFragment()
    }

    private lateinit var listViewModel: TransactionsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transactions_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listViewModel = ViewModelProviders.of(this).get(TransactionsListViewModel::class.java)
        // TODO: Use the ViewModel

        val gnbService = GNBService()
        GlobalScope.launch(Dispatchers.Main) {
            val transactionsResponse = gnbService.getTransactions().await()
            textViewTransactions.text = transactionsResponse.subList(0,11).toString()
        }
    }

}
