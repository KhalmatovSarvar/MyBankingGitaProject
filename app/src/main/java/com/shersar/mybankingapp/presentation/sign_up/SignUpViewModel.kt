package com.shersar.mybankingapp.presentation.sign_up

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel

interface SignUpViewModel {
    val messageLiveData: LiveData<String>
    val isEnableActionButtonLiveData: LiveData<Boolean>
    val openVerifyLiveData: LiveData<Unit>
    val errorMessage: LiveData<String>

    fun signUp(
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        phoneNumber: String,
        password: String,
        gender: Int
    )
}