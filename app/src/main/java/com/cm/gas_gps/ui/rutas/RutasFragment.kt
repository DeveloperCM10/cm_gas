package com.cm.gas_gps.ui.rutas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cm.gas_gps.R
import com.cm.gas_gps.databinding.FragmentRutasBinding
import com.cm.gas_gps.ui.consumos.ConsumosFragment

class RutasFragment : Fragment(){
    private lateinit var binding: FragmentRutasBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRutasBinding.inflate(inflater,null,false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        this.initializeListener()
//        this.initializeObservers()

    }

    fun initializeListener(){

        with(binding){
            txtRutas.setOnClickListener {

//                val fragmentB = ConsumosFragment()
//                activity?.getSupportFragmentManager()?.beginTransaction()
//                    ?.replace(R.id.acvityNavigationNav, fragmentB, "fragmentConsumos")
//                    ?.addToBackStack("const_rutas")
//                    ?.commit();

                findNavController().navigate(RutasFragmentDirections.actionNextToRutas())

            }

        }
    }
}