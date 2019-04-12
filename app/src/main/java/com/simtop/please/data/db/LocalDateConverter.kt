package com.simtop.please.data.db

import androidx.room.TypeConverter
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter


object LocalDateConverter {
    @TypeConverter
    @JvmStatic
    fun stringToDate(str: String?) = str?.let {
        ZonedDateTime.parse(it, DateTimeFormatter.ISO_ZONED_DATE_TIME)
    }

    @TypeConverter
    @JvmStatic
    fun dateToString(dateTime: ZonedDateTime?) = dateTime?.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
}