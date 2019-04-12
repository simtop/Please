package com.simtop.please.data.provider

import com.simtop.please.util.SystemType

interface UnitProvider {
    fun getUnitType() : SystemType
}