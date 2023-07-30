package com.shersar.mybankingapp.presentation.sign_up

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
import com.shersar.mybankingapp.databinding.FragmentSignUpBinding
import com.shersar.mybankingapp.presentation.sign_in.SignInViewModel
import com.shersar.mybankingapp.presentation.sign_in.SignInViewModelImpl
import com.shersar.mybankingapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpScreen : Fragment(R.layout.fragment_sign_up) {
    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: SignUpViewModel by viewModels<SignUpViewModelImpl>()
    private var isValidPhone = false
    private var isValidPassword = false
    private var isValidFirstName = false
    private var isValidLastName = false
    private var isValiddateOfBirth = false
    private var isCheckedGender = false
    private val datePattern = """^\d{1,2}/\d{1,2}/\d{2}$"""
    private var gender = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.errorMessage.observe(this, errorObserver)
        viewModel.messageLiveData.observe(this, messageObserver)
        viewModel.openVerifyLiveData.observe(this, openVerifyObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isEnableActionButtonLiveData.observe(
            viewLifecycleOwner,
            isEnableActionButtonObserver
        )
        inputValidationCHeck()

        binding.signUpButton.setOnClickListener {
            viewModel.signUp(
                binding.firstNameEditText.text.toString(),
                binding.lastNameEditText.text.toString(),
                binding.dateOfBirthEditText.text.toString(),
                binding.phoneNumberEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                gender
            )
        }

        binding.genderRadioGroup.setOnCheckedChangeListener { _, _ ->
            if(binding.genderRadioGroup.checkedRadioButtonId == binding.maleRadioButton.id){
                gender = 0
                isCheckedGender = true

            }else if(binding.genderRadioGroup.checkedRadioButtonId == binding.femaleRadioButton.id){
                gender = 1
                isCheckedGender = true

            }else{
                isCheckedGender = false
            }
        }

    }

    private val messageObserver = Observer<String> { requireContext().showToast(it) }
    private val errorObserver = Observer<String> { requireContext().showToast("Error = $it") }
    private val openVerifyObserver =
        Observer<Unit> { findNavController().navigate(R.id.action_signUpFragment_to_verifySmsFragment) }
    private val isEnableActionButtonObserver =
        Observer<Boolean> { binding.signUpButton.isEnabled = it }


    private fun inputValidationCHeck() {
        binding.passwordEditText.doAfterTextChanged {
            it?.let {
                isValidPassword = it.length > 3
                binding.signUpButton.isEnabled =
                    isValidPassword && isValidFirstName && isValidLastName && isValidPhone && isValiddateOfBirth && isCheckedGender
            }
        }
        binding.phoneNumberEditText.doAfterTextChanged {
            it?.let {
                isValidPhone = it.startsWith("+998") && it.length == 13
                binding.signUpButton.isEnabled =
                    isValidPassword && isValidFirstName && isValidLastName && isValidPhone && isValiddateOfBirth && isCheckedGender

            }
        }
        binding.firstNameEditText.doAfterTextChanged {
            it?.let {
                isValidFirstName = it.length >= 3
                binding.signUpButton.isEnabled =
                    isValidPassword && isValidFirstName && isValidLastName && isValidPhone && isValiddateOfBirth && isCheckedGender
            }
        }
        binding.lastNameEditText.doAfterTextChanged {
            it?.let {
                isValidLastName = it.length >= 3
                binding.signUpButton.isEnabled =
                    isValidPassword && isValidFirstName && isValidLastName && isValidPhone && isValiddateOfBirth && isCheckedGender
            }
        }

        binding.dateOfBirthEditText.doAfterTextChanged {
            it?.let {
                isValiddateOfBirth = Regex(datePattern).matches(it)
                binding.signUpButton.isEnabled =
                    isValidPassword && isValidFirstName && isValidLastName && isValidPhone && isValiddateOfBirth && isCheckedGender
            }
        }

    }


}
