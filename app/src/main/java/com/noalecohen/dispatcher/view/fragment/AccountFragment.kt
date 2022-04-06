package com.noalecohen.dispatcher.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.noalecohen.dispatcher.databinding.FragmentAccountBinding
import com.noalecohen.dispatcher.view.activity.AuthActivity
import com.noalecohen.dispatcher.viewmodel.AuthViewModel


class AccountFragment : Fragment() {
    private val authModel: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSignoutButton()
    }

    private fun setSignoutButton() {
        binding.accountSignoutButton.setOnClickListener {
            authModel.signOut()
            var intent = Intent(activity, AuthActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}
