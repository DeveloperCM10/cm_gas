package com.cm.gas_gps.ui.rutas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.adapters.rutas.RutasAdapter
import com.cm.gas_gps.databinding.FragmentRutasBinding
import com.cm.gas_gps.utils.SwipeGesture.SwipeGesture

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
        this.initializeAdapter()
        with(binding){
            btnAddGeofences.setOnClickListener {
                findNavController().navigate(RutasFragmentDirections.actionNextToGeofences())
            }
        }
    }

    private fun initializeAdapter() {
        val swipegesture  = object : SwipeGesture(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when(direction){
                    ItemTouchHelper.LEFT ->{

                    }

                }

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipegesture)
        itemTouchHelper.attachToRecyclerView( binding.rvListaRutas)

        binding.rvListaRutas.adapter = RutasAdapter()
    }
}