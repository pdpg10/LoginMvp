package com.example.loginmvp.widget.pref.data

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LongPreference(
    private val pref: SharedPreferences,
    private val key: String,
    private val defValue: Long = 0L
) : ReadWriteProperty<Any, Long> {
    override fun getValue(thisRef: Any, property: KProperty<*>) = pref.getLong(key, defValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
        pref.edit()
            .putLong(key, value)
            .apply()
    }

}