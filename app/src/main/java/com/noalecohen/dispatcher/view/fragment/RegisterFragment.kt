package com.noalecohen.dispatcher.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.FragmentRegisterBinding
import com.noalecohen.dispatcher.view.activity.AuthActivity
import com.noalecohen.dispatcher.view.activity.MainActivity
import com.noalecohen.dispatcher.viewmodel.AuthViewModel
import com.noalecohen.dispatcher.viewstate.ViewState

class RegisterFragment : Fragment() {
    private val model: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentRegisterBinding

    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var verifyPasswordEditText: TextInputEditText
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var verifyPasswordLayout: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        bindView()
        subscribeObservers()
        return binding.root
    }

    private fun bindView() {
        emailEditText = binding.registerEmailEditText
        passwordEditText = binding.registerPasswordEditText
        verifyPasswordEditText = binding.registerVerifyPasswordEditText
        emailLayout = binding.registerEmailLayout
        passwordLayout = binding.registerPasswordLayout
        verifyPasswordLayout = binding.registerVerifyPasswordLayout
        setSignupButton()
        setSwitchToLoginButton()

        emailEditText.addTextChangedListener {
            resetEditTextView(emailEditText, emailLayout)
        }
        passwordEditText.addTextChangedListener {
            resetEditTextView(passwordEditText, passwordLayout)
        }
        verifyPasswordEditText.addTextChangedListener {
            resetEditTextView(verifyPasswordEditText, verifyPasswordLayout)
        }
    }

    private fun setSignupButton() {
        binding.registerSignupButton.setOnClickListener {
            val email = emailEditText.text.toString().trim { it <= ' ' }
            val password = passwordEditText.text.toString().trim { it <= ' ' }
            val verifyPassword = verifyPasswordEditText.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                setErrorViewForEditText(emailEditText, emailLayout, EMPTY_EMAIL_ERROR_MESSAGE)
            } else {
                resetEditTextView(emailEditText, emailLayout)
            }
            if (password.isEmpty()) {
                setErrorViewForEditText(
                    passwordEditText,
                    passwordLayout,
                    EMPTY_PASSWORD_ERROR_MESSAGE
                )
            } else {
                resetEditTextView(passwordEditText, passwordLayout)
            }
            if (verifyPassword.isEmpty()) {
                setErrorViewForEditText(
                    verifyPasswordEditText,
                    verifyPasswordLayout,
                    EMPTY_VERIFY_PASSWORD_ERROR_MESSAGE
                )
            } else {
                resetEditTextView(verifyPasswordEditText, verifyPasswordLayout)
            }

            if (password.isNotEmpty() && verifyPassword.isNotEmpty() && !password.equals(
                    verifyPassword
                )
            ) {
                setErrorViewForEditText(passwordEditText, passwordLayout, DIFFERENT_PASSWORDS)
                setErrorViewForEditText(
                    verifyPasswordEditText,
                    verifyPasswordLayout,
                    DIFFERENT_PASSWORDS
                )
            }

            if (email.isNotEmpty() && password.isNotEmpty() && verifyPassword.isNotEmpty() && password.equals(
                    verifyPassword
                )
            ) {
                model.register(email, password)
            }
        }
    }

    private fun setSwitchToLoginButton() {
        binding.registerSigninButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.startup_frame_content, LoginFragment())
                ?.commit()
        }
    }

    private fun subscribeObservers() {
        model.viewStateLiveDataRegister.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    model.viewStateLiveDataRegister.postValue(ViewState.Idle)
                    (activity as AuthActivity).hideProgressBar()
                    activity?.let { fragmentActivity ->
                        val intent = Intent(fragmentActivity, MainActivity::class.java)
                        fragmentActivity.startActivity(intent)
                    }
                }
                is ViewState.Error -> {
                    model.viewStateLiveDataRegister.postValue(ViewState.Idle)
                    (activity as AuthActivity).hideProgressBar()
                    Toast.makeText(activity, it.error?.message, Toast.LENGTH_LONG)
                        .show()
                }
                is ViewState.Loading -> (activity as AuthActivity).showProgressBar()
            }
        }
    }

    private fun setErrorViewForEditText(
        editText: TextInputEditText,
        editTextLayout: TextInputLayout, errorMessage: String
    ) {
        editText.setBackgroundResource(R.drawable.error_edit_text_background)
        editText.setHintTextColor(
            ResourcesCompat.getColor(
                resources,
                R.color.auth_hint,
                null
            )
        )
        editTextLayout.isErrorEnabled = true
        editTextLayout.error = errorMessage
    }

    private fun resetEditTextView(editText: TextInputEditText, editTextLayout: TextInputLayout) {
        editText.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.white,
                null
            )
        )
        editText.setBackgroundResource(R.drawable.edit_text_background)
        editText.setHintTextColor(
            ResourcesCompat.getColor(
                resources,
                R.color.auth_hint,
                null
            )
        )
        editTextLayout.isErrorEnabled = false
    }

    companion object {
        const val EMPTY_EMAIL_ERROR_MESSAGE = "Please enter your email address"
        const val EMPTY_PASSWORD_ERROR_MESSAGE = "Please enter your password"
        const val EMPTY_VERIFY_PASSWORD_ERROR_MESSAGE = "Please enter your password again"
        const val DIFFERENT_PASSWORDS = "Passwords are not the same"
    }

}