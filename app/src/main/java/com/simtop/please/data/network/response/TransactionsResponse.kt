package com.simtop.please.data.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions_list")
data class TransactionsResponse(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val sku: String,
    val amount: String,
    val currency: String
)