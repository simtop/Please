package com.simtop.please.ui.transactions.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.simtop.please.R
import com.simtop.please.data.network.response.TransactionsResponse
import com.simtop.please.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.transactions_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class TransactionsListFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: TransactionsListViewModelFactory by instance()

    private lateinit var viewModel: TransactionsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transactions_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TransactionsListViewModel::class.java)

        bindUI()


    }

    private fun bindUI() = launch {

        val transactionsList = viewModel.transactions.await()

        updateAppName()
        updateFragmentName()
        transactionsList.observe(this@TransactionsListFragment, Observer {
            if (it == null) return@Observer
            //textViewTransactions.text = it.subList(0,7).toString() + " \n" + "\n"

            group_loading.visibility = View.GONE
            initRecyclerView(it.toTransactionResponseItems())

        })
    }

    private fun initRecyclerView(items: List<TransactionsListItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TransactionsListFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? TransactionsListItem)?.let {
                showTransactionDetail(it.transactionEntry.sku,view)
            }
        }
    }

    private fun showTransactionDetail(detailSku : String, view : View){
        val actionDetail = TransactionsListFragmentDirections.actionDetail(detailSku)
        Navigation.findNavController(view).navigate(actionDetail)
    }

    private fun List<TransactionsResponse>.toTransactionResponseItems(): List<TransactionsListItem> {
        return this.map {
            TransactionsListItem(it)
        }
    }

    private fun updateFragmentName() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = "List of Transactions"
    }

    private fun updateAppName() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "P.L.E.A.S.E."
    }
}