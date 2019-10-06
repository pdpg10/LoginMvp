package com.example.loginmvp.repo

import androidx.lifecycle.LiveData

interface ISignInRepo {
    fun signIn(login: String, pass: String): LiveData<State>

    fun cancel()
}