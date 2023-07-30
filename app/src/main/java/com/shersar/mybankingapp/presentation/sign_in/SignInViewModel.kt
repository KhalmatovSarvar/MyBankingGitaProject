package com.shersar.mybankingapp.presentation.sign_in

import androidx.lifecycle.LiveData

interface SignInViewModel {
    val messageLiveData: LiveData<String>
    val isEnableActionButtonLiveData : LiveData<Boolean>
    val openVerifyLiveData: LiveData<Unit>
    val errorMessage: LiveData<String>

    fun signIn(password: String, phone: String)
}