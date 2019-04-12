package com.simtop.please.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.simtop.please.util.SystemType

const val CURRENCY_TYPE ="CURRENCY_TYPE"

class UnitProviderImpl(context : Context) : UnitProvider {
    private val appContext = context.applicationContext

    private val preferences : SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)
    override fun getUnitType(): SystemType {
        val selectedName = preferences.getString(CURRENCY_TYPE,SystemType.EUR.name)
        return SystemType.valueOf(selectedName!!)
    }
}