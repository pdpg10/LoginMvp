package com.example.loginmvp.repo

sealed class State
data class FAIL(val message: String) : State()
object SUCCESS : State()
object UNKNOWN : State()