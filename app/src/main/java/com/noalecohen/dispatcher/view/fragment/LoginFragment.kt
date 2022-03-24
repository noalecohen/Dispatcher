package com.noalecohen.dispatcher.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.FragmentLoginBinding
import com.noalecohen.dispatcher.view.activity.MainActivity
import com.noalecohen.dispatcher.viewmodel.AuthViewModel
import com.noalecohen.dispatcher.viewstate.ViewState

class LoginFragment : Fragment() {
    private val model: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

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
        setSignupButton()
        setSwitchToRegisterButton()
    }

    private fun setSignupButton() {
        binding.loginSigninButton.setOnClickListener {
            val email = emailEditText.text.toString().trim { it <= ' ' }
            val password = passwordEditText.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                setErrorViewForEditText(emailEditText)
            } else {
                resetEditTextView(emailEditText)
            }
            if (password.isEmpty()) {
                setErrorViewForEditText(passwordEditText)
            } else {
                resetEditTextView(passwordEditText)
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
                is ViewState.Success -> activity?.let { fragmentActivity ->
                    val intent = Intent(fragmentActivity, MainActivity::class.java)
                    fragmentActivity.startActivity(intent)
                }
                is ViewState.Error -> Toast.makeText(activity, it.error?.message, Toast.LENGTH_LONG)
                    .show()
                is ViewState.Loading -> Log.d("Test", "Loading") //TODO: Implement Loader
            }
        }
    }

    private fun setErrorViewForEditText(editText: EditText) {
        editText.setBackgroundResource(R.drawable.error_edit_text_background)
        editText.setHintTextColor(ResourcesCompat.getColor(resources, R.color.error_message, null))
    }

    private fun resetEditTextView(editText: EditText) {
        editText.setBackgroundResource(R.drawable.edit_text_background)
        editText.setHintTextColor(ResourcesCompat.getColor(resources, R.color.auth_hint, null))
    }

}