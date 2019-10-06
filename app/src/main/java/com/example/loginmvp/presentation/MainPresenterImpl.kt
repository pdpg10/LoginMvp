package com.example.loginmvp.presentation

import android.util.Log
import androidx.lifecycle.Observer
import com.example.loginmvp.repo.SUCCESS
import com.example.loginmvp.repo.SignInRepo
import com.example.loginmvp.repo.State
import com.example.loginmvp.ui.IMainView

class MainPresenterImpl : IMainPresenter {
    private var view: IMainView? = null
    private val repo = SignInRepo.instance()

    override fun onLogin(login: String, pass: String) {
        val localView = view
        if (localView != null) {
            Log.d("onLogin", "onLogin $pass $login")
            localView.showProgress()
            repo.signIn(login, pass)
                .observe(localView.provideLifeCycle(),
                    Observer { onResult(it) })
        }

    }

    private fun onResult(it: State?) {
        if (it == null) return
        Log.d("onResult", "onResult $it")

        if (it == SUCCESS) {
            view?.showSuccess()
        } else {
            view?.hideProgress()
            view?.showAlert()
        }
    }

    override fun onAttachView(view: IMainView) {
        this.view = view
    }

    override fun onDetachView() {
        view = null
    }
}