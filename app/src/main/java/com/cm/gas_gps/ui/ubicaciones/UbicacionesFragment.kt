package com.cm.gas_gps.ui.ubicaciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cm.gas_gps.R
import com.cm.gas_gps.databinding.FragmentUbicacionesBinding
import com.cm.gas_gps.ui.rutas.RutasFragment


class UbicacionesFragment : Fragment(){
    private lateinit var binding: FragmentUbicacionesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUbicacionesBinding.inflate(inflater,null,false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        this.initializeListener()

    }

    fun initializeListener(){
        with(binding){
//            txtPrueba.setOnClickListener {
//
//                val fragmentB = UbicacionesFragment()
//                activity?.getSupportFragmentManager()?.beginTransaction()
//                    ?.replace(R.id.acvityNavigationNav, fragmentB, "fragmnetUbicaciones")
//                    ?.commit();
//            }

        }
    }
}
