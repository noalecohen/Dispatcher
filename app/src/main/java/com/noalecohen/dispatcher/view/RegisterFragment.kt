package com.noalecohen.dispatcher.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.FragmentRegisterBinding
import com.noalecohen.dispatcher.viewmodel.AuthViewModel
import com.noalecohen.dispatcher.viewstate.ViewState


class RegisterFragment : Fragment() {
    private val model: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentRegisterBinding

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var verifyPasswordEditText: EditText

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
        setSignupButton()
        setSwitchToLoginButton()
    }

    private fun setSignupButton() {
        binding.registerSignupButton.setOnClickListener {
            val email = emailEditText.text.toString().trim { it <= ' ' }
            val password = passwordEditText.text.toString().trim { it <= ' ' }
            val verifyPassword = verifyPasswordEditText.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                //TODO: change emailEditText to red and print error message
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
        model.viewStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success ->
                    activity?.let { fragmentActivity ->
                        val intent = Intent(fragmentActivity, MainActivity::class.java)
                        fragmentActivity.startActivity(intent)
                    }
                is ViewState.Error -> Toast.makeText(activity, it.error?.message, Toast.LENGTH_LONG)
                    .show()
                is ViewState.Loading -> Log.d("Test", "Loading") //TODO: handle loading state
            }
        }
    }

}