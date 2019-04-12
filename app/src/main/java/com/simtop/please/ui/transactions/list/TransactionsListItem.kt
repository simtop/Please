package com.simtop.please.ui.transactions.list

import com.simtop.please.R
import com.simtop.please.data.network.response.TransactionsResponse
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.items_list_transitions.*

class TransactionsListItem(
    val transactionEntry: TransactionsResponse
) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_Sku.text = transactionEntry.sku
            textView_money.text = transactionEntry.amount
            textView_type.text = transactionEntry.currency
        }
    }

    override fun getLayout() = R.layout.items_list_transitions

}