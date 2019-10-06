package com.example.loginmvp.presentation

import com.example.loginmvp.ui.IMainView

interface IMainPresenter {
    fun onLogin(login: String, pass: String)

    fun onAttachView(view: IMainView)

    fun onDetachView()
}