package com.shersar.mybankingapp.presentation.sign_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shersar.mybankingapp.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class SignInViewModelImpl @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel(), SignInViewModel {
    override val messageLiveData = MutableLiveData<String>()
    override val isEnableActionButtonLiveData = MutableLiveData<Boolean>()
    override val openVerifyLiveData = MutableLiveData<Unit>()
    override val errorMessage = MutableLiveData<String>()

    override fun signIn(password: String, phone: String) {
        // check network valid
        authRepository.signIn(password, phone)
            .onStart {
                messageLiveData.value = "Start"
                isEnableActionButtonLiveData.value = false
            }
            .onEach {
                it.onSuccess {
                    messageLiveData.value = "Success"
                    openVerifyLiveData.value = Unit
                }

                it.onFailure { throwable ->
                    errorMessage.value = throwable.message
                    isEnableActionButtonLiveData.value = true
                }
            }
            .launchIn(viewModelScope)
    }

}
