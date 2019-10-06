package com.example.loginmvp.widget.dialog

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import com.example.loginmvp.R
import com.example.loginmvp.ui.MainActivity
import kotlin.reflect.KProperty

class ProgressDialogImpl : IProgressDialog {

    private var dialog: AlertDialog? = null

    override fun showProgress(context: Context, isCancelable: Boolean) {
        dialog = AlertDialog.Builder(context)
            .setView(R.layout.progress_layout)
            .setCancelable(isCancelable)
            .create()
        dialog?.show()
    }

    override fun hideProgress() {
        Log.d("hideProgress", "hideProgress")
        dialog?.dismiss()
    }

    operator fun getValue(mainActivity: MainActivity, property: KProperty<*>): IProgressDialog {
        return ProgressDialogImpl()
    }
}