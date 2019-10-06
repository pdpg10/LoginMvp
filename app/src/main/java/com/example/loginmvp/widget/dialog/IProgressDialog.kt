package com.example.loginmvp.widget.dialog

import android.content.Context

interface IProgressDialog {
    fun showProgress(context: Context, isCancelable: Boolean = false)

    fun hideProgress()
}