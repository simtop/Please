package com.simtop.please.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simtop.please.data.network.response.RatesResponse

@Dao
interface RatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rateList: List<RatesResponse>)

    @Query("SELECT * FROM rates_list")
    fun getListRates(): LiveData<List<RatesResponse>>


    @Query("DELETE FROM rates_list")
    fun deleteOldEntries()

}