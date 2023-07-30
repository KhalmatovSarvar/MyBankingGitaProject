package com.shersar.mybankingapp.presentation.sign_in

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shersar.mybankingapp.R
import com.shersar.mybankingapp.databinding.FragmentSignInBinding
import com.shersar.mybankingapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInScreen : Fragment(R.layout.fragment_sign_in) {
  private val binding by viewBinding(FragmentSignInBinding::bind)
  private val viewModel: SignInViewModel by viewModels<SignInViewModelImpl>()
  private var isValidPhone = false
  private var isValidPassword = false

  override fun onCreate(savedInstanceState: Bundle?) {
    viewModel.messageLiveData.observe(this, messageObserver)
    viewModel.errorMessage.observe(this, errorObserver)
    viewModel.openVerifyLiveData.observe(this, openVerifyObserver)
    super.onCreate(savedInstanceState)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    viewModel.isEnableActionButtonLiveData.observe(viewLifecycleOwner, isEnableActionButtonObserver)
    addInputValidation()

    binding.signInButton.setOnClickListener {
      viewModel.signIn(binding.passwordEditText.text.toString(), binding.phoneNumberEditText.text.toString())
    }
  }

  private val messageObserver = Observer<String> { requireContext().showToast(it) }
  private val errorObserver = Observer<String> { requireContext().showToast("Error = $it") }
  private val openVerifyObserver = Observer<Unit> { findNavController().navigate(R.id.action_signInScreen_to_verifySmsFragment) }
  private val isEnableActionButtonObserver = Observer<Boolean> { binding.signInButton.isEnabled = it }
  private fun addInputValidation() {
    binding.passwordEditText.doAfterTextChanged {
      it?.let {
        isValidPassword = it.length > 3
        binding.signInButton.isEnabled = isValidPassword && isValidPhone
      }
    }

    binding.phoneNumberEditText.doAfterTextChanged {
      it?.let {
        isValidPhone = it.length == 13 && it.startsWith("+998")
        binding.signInButton.isEnabled = isValidPassword && isValidPhone
      }
    }
  }
}