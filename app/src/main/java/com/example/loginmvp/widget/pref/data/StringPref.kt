package com.example.loginmvp.widget.pref.data

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringPreference(
    private val pref: SharedPreferences,
    private val key: String,
    private val defValue: String = ""
) : ReadWriteProperty<Any, String> {

    override fun getValue(thisRef: Any, property: KProperty<*>) =
        pref.getString(key, defValue) ?: defValue

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        pref.edit()
            .putString(key, value)
            .apply()
    }

}