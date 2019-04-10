package com.simtop.please.data.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates_list")
data class RatesResponse(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val from: String,
    val to: String,
    val rate: String)
