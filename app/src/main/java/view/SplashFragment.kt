package view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.FragmentSplashBinding
import viewModel.AuthViewModel

class SplashFragment : Fragment() {
    private val model: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler().postDelayed({
            switchToRelevantFragment()
        }, 3000)

        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun switchToRelevantFragment() {
        if (model.getCurrentUser() == null) {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.startup_frame_content, RegisterFragment())?.commit()
        } else {
            activity?.let {
                val intent = Intent(it, MainActivity::class.java)
                it.startActivity(intent)
            }
        }
    }
}