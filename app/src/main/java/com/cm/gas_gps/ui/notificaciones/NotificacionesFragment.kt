package com.cm.gas_gps.ui.notificaciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.adapters.notificaciones.NotificacionesAdapter
import com.cm.gas_gps.databinding.FragmentNotificacionesBinding
import com.cm.gas_gps.ui.navigation.NavigationActivity
import com.cm.gas_gps.ui.ubicaciones.UbicacionesViewModel
import com.cm.gas_gps.utils.SwipeGesture.SwipeGesture

class NotificacionesFragment: Fragment() {
    private lateinit var binding: FragmentNotificacionesBinding
    private val viewModel: NotificacionesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificacionesBinding.inflate(inflater,null,false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        this.initialListener()
        this.initializeObserver()

    }

    private fun initialListener() {
        viewModel.loadingMostrar()
        this.initializeAdapter()

        with(binding){

        }
    }

    fun initializeObserver(){
        viewModel.loading.observe(viewLifecycleOwner){
            if (it) {
                binding.loadingNotificaciones.visibility = View.VISIBLE
            }
            else{
                binding.loadingNotificaciones.visibility= View.GONE
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
        itemTouchHelper.attachToRecyclerView( binding.rvListaNotificaciones)

        binding.rvListaNotificaciones.adapter = NotificacionesAdapter()
        viewModel.loadingCerrar()
    }
}