package com.simtop.please.ui.transactions.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.simtop.please.R

class TransactionsDetailFragment : Fragment() {

    companion object {
        fun newInstance() = TransactionsDetailFragment()
    }

    private lateinit var viewModel: TransactionsDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transactions_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TransactionsDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
