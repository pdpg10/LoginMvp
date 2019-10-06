package com.example.loginmvp.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginmvp.repo.api.Api
import com.example.loginmvp.repo.model.SignInRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SignInRepo(private val api: Api) : ISignInRepo, CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = job
//    private val liveData = MutableLiveData<State>()

    override fun signIn(login: String, pass: String): LiveData<State> {
        val liveData = MutableLiveData<State>()
        launch {
            try {
                val req = SignInRequest(login, pass)
                Log.d("signIn", "onLogin $pass $login")
                val data = api.singIn(req)
                if (data.success != null) {
                    liveData.postValue(SUCCESS)
                } else {
                    liveData.postValue(FAIL(data.error?.message ?: ""))
                }
            } catch (e: Exception) {
                liveData.postValue(UNKNOWN)
            }
        }
        return liveData
    }

    override fun cancel() {
//        job.cancel()
    }

    companion object {
        private var hiddenRepo: ISignInRepo? = null

        fun instance(): ISignInRepo {
            var localRepo = hiddenRepo
            if (localRepo == null) {
                localRepo = SignInRepo(Api.instance())
                hiddenRepo = localRepo
            }
            return hiddenRepo!!
        }
    }

}