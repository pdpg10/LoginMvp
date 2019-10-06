package com.example.loginmvp.widget.pref

import android.content.SharedPreferences
import com.example.loginmvp.widget.pref.data.LongPreference
import com.example.loginmvp.widget.pref.data.StringPreference

private const val KEY_TOKEN = "KEY_TOKEN"
private const val KEY_USER_ID = "KEY_USER_ID"

class PreferenceImpl(pref: SharedPreferences) : IPreference {

    override var token: String by StringPreference(pref, KEY_TOKEN)

    override var userId: Long by LongPreference(pref, KEY_USER_ID)
}
