package com.simtop.please.util

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "Please"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences



    private val DOWNLOAD_TIME = "DOWNLOAD_TIME"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var downloadTime: String
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getString(DOWNLOAD_TIME, "SIMON")

        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putString(DOWNLOAD_TIME, value)
        }
}