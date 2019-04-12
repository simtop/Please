package com.simtop.please.data.network.response

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "transactions_list", indices = [Index(value = ["sku"], unique = false)])
data class TransactionsResponse(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val sku: String,
    val amount: String,
    val currency: String
)