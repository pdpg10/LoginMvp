package com.example.loginmvp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.example.loginmvp.R
import com.example.loginmvp.presentation.IMainPresenter
import com.example.loginmvp.presentation.MainPresenterImpl
import com.example.loginmvp.widget.dialog.ProgressDialogImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), IMainView {

    private var alert: AlertDialog? = null
    private val progressDialogDelegation by ProgressDialogImpl()
    private lateinit var presenter: IMainPresenter
    private var dialog: android.app.AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter = MainPresenterImpl()

        bt_login.setOnClickListener {
            onLogin()
        }
    }

    private fun onLogin() {
        val login = et_login.text.toString()
        val pass = et_password.text.toString()
        presenter.onLogin(login, pass)
        bt_login.hideKeyboard()

    }

    override fun showProgress() {
        Log.d("showProgress", "showProgress")
        dialog = android.app.AlertDialog.Builder(this)
            .setView(R.layout.progress_layout)
            .setCancelable(true)
            .create()
        dialog?.show()
    }

    override fun onResume() {
        super.onResume()
        presenter.onAttachView(this)
    }

    override fun hideProgress() {
        dialog?.dismiss()
    }

    override fun showAlert() {
        alert = AlertDialog.Builder(this)
            .setTitle("Alert")
            .setMessage("Something won't wrong, try again")
            .setPositiveButton("ok", null)
            .create()
        alert?.show()
    }

    override fun showSuccess() {
        hideProgress()
        alert?.dismiss()
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    override fun provideLifeCycle(): LifecycleOwner = this

    override fun onStop() {
        super.onStop()
        progressDialogDelegation.hideProgress()
        presenter.onDetachView()
    }
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}
