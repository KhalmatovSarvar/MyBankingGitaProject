package com.shersar.mybankingapp.presentation.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shersar.mybankingapp.data.sources.remote.AuthApi
import com.shersar.mybankingapp.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class SignUpViewModelImpl @Inject constructor(
   private val authRepository: AuthRepository
) :ViewModel(),SignUpViewModel {
    override val messageLiveData = MutableLiveData<String>()
    override val isEnableActionButtonLiveData = MutableLiveData<Boolean>()
    override val openVerifyLiveData = MutableLiveData<Unit>()
    override val errorMessage = MutableLiveData<String>()

    override fun signUp(
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        phoneNumber: String,
        password: String,
        gender: Int
    ) {
       authRepository.signUp(firstName, lastName, dateOfBirth, phoneNumber, password, gender)
           .onStart {
               messageLiveData.value = "start"
               isEnableActionButtonLiveData.value = false
           }
           .onEach {
               it.onSuccess {
                   messageLiveData.value = "Success"
                   openVerifyLiveData.value = Unit
               }
               it.onFailure { throwable ->
                   errorMessage.value = "Failure"
                   isEnableActionButtonLiveData.value = true
               }
           }.launchIn(viewModelScope)



    }
}