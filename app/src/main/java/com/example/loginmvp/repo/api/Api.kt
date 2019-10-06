package com.example.loginmvp.repo.api

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.loginmvp.App
import com.example.loginmvp.repo.model.BaseData
import com.example.loginmvp.repo.model.SignInRequest
import com.example.loginmvp.repo.model.SignInResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("api/sign_in")
    suspend fun singIn(@Body req: SignInRequest): BaseData<SignInResponse>

    companion object {
        private var hiddenApi: Api? = null

        fun instance(): Api {
            var localApi = hiddenApi
            if (localApi == null) {
                val okhttpClient = OkHttpClient.Builder()
                    .addInterceptor(ChuckerInterceptor(App.context!!))
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://still-sea-55638.herokuapp.com/")
                    .client(okhttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                localApi = retrofit.create(Api::class.java)
                hiddenApi = localApi
            }
            return hiddenApi!!
        }
    }
}