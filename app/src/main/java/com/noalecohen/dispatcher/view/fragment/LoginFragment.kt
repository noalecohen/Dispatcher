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
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.FragmentLoginBinding
import com.noalecohen.dispatcher.view.activity.AuthActivity
import com.noalecohen.dispatcher.view.activity.MainActivity
import com.noalecohen.dispatcher.viewmodel.AuthViewModel
import com.noalecohen.dispatcher.viewstate.ViewState
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private val model: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding

    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        bindView()
        subscribeObservers()
        return binding.root
    }

    private fun bindView() {
        emailEditText = binding.loginEmailEditText
        passwordEditText = binding.loginPasswordEditText
        emailLayout = binding.loginEmailLayout
        passwordLayout = binding.loginPasswordLayout
        setSignupButton()
        setSwitchToRegisterButton()
        initInput()
        setInputListeners()
        collectInputFlow()
    }

    private fun setSignupButton() {
        binding.loginSigninButton.setOnClickListener {
            val email = emailEditText.text.toString().trim { it <= ' ' }
            val password = passwordEditText.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                setErrorViewForEditText(
                    emailEditText,
                    emailLayout,
                    getString(R.string.empty_email_error_message)
                )
            }
            if (password.isEmpty()) {
                setErrorViewForEditText(
                    passwordEditText,
                    passwordLayout,
                    getString(R.string.empty_password_error_message)
                )
            }

            if (email.isNotEmpty() && password.isNotEmpty()) {
                model.login(email, password)
            }
        }
    }

    private fun setSwitchToRegisterButton() {
        binding.loginSignupButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.startup_frame_content, RegisterFragment())?.commit()
        }
    }

    private fun subscribeObservers() {
        model.viewStateLiveDataLogin.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    (activity as AuthActivity).showLoader(false)
                    activity?.let { fragmentActivity ->
                        val intent = Intent(fragmentActivity, MainActivity::class.java)
                        fragmentActivity.startActivity(intent)
                        fragmentActivity.finish()
                    }
                }

                is ViewState.Error -> {
                    model.resetViewStateLiveDataLogin()
                    (activity as AuthActivity).showLoader(false)
                    Toast.makeText(activity, it.error?.message, Toast.LENGTH_LONG)
                        .show()
                }
                is ViewState.Loading -> (activity as AuthActivity).showLoader(true)
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

    private fun initInput() {
        model.setEmail("")
        model.setPassword("")
        model.setVerifyPassword("")
    }

    private fun setInputListeners() {
        emailEditText.addTextChangedListener {
            resetEditTextView(emailEditText, emailLayout)
            model.setEmail(it.toString())
        }
        passwordEditText.addTextChangedListener {
            resetEditTextView(passwordEditText, passwordLayout)
            model.setPassword(it.toString())
            model.setVerifyPassword(it.toString())
        }
    }

    private fun collectInputFlow() {
        lifecycleScope.launch {
            model.isSubmitEnabled.collect {
                binding.loginSigninButton.isEnabled = it
            }
        }
    }

}