package com.example.loginmvp.ui

import androidx.lifecycle.LifecycleOwner

interface IMainView {
    fun showProgress()

    fun hideProgress()

    fun showAlert()

    fun showSuccess()

    fun provideLifeCycle(): LifecycleOwner
}