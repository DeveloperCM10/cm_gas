package com.cm.gas_gps.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.cm.gas_gps.databinding.FragmentLoginBinding
import com.cm.gas_gps.ui.navigation.NavigationActivity

class LoginFragment : Fragment(){
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,null,false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        this.initialListener()
        this.initializeObservers()

    }

    private fun initializeObservers(){

    }

    private fun initialListener() {
        with(binding) {
            btnLoginLogin.setOnClickListener {
                NavigationActivity.startActivity(requireContext())
            }
        }
    }
}