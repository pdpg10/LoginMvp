package com.example.loginmvp.repo.model


class BaseData<T>(val error: ErrorCode? = null,
                  val success: T? = null)