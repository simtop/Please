package com.simtop.please.ui.transactions.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.simtop.please.R
import com.simtop.please.data.GNBService
import com.simtop.please.util.DecimalOperations
import kotlinx.android.synthetic.main.transactions_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigDecimal

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
            //textViewTransactions.text = transactionsResponse.toString()
            var round = "0"
            //var round = BigDecimal("0").setScale(2,BigDecimal.ROUND_HALF_EVEN)
            var total = 0
            val compar = transactionsResponse[0].sku
            val hola = transactionsResponse.subList(0,11).forEach {
                BigDecimal(it.amount).setScale(2,BigDecimal.ROUND_HALF_EVEN)
                if(it.sku  == compar){
                    total += 1
             //       round = (round + BigDecimal(it.amount).setScale(2,BigDecimal.ROUND_HALF_EVEN)).setScale(2,BigDecimal.ROUND_HALF_EVEN)
                    round = DecimalOperations.sumDecimalOperations(round,it.amount)
                }

            }
            textViewTransactions.text = total.toString() + "\n" + round + "\n" + transactionsResponse.subList(0,11)
        }
    }

}