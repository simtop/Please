package com.simtop.please.ui.transactions.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.simtop.please.R

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
    }

}
