package com.cm.gas_gps.ui.vehiculos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.R
import com.cm.gas_gps.adapters.vehiculos.VehiculosAdapter
import com.cm.gas_gps.databinding.FragmentVehiculosBinding
import com.cm.gas_gps.dialogs.DialogAsignarAuto
import com.cm.gas_gps.dialogs.DialogVehiculo
import com.cm.gas_gps.utils.SwipeGesture.SwipeGesture
import com.cm.gas_gps.utils.SwipeGesture.SwipeGestureVehiculos
import com.cm.gas_gps.utils.extensions.AccionesDialog

class VehiculosFragment: Fragment() {
    private lateinit var binding: FragmentVehiculosBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVehiculosBinding.inflate(inflater,null,false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        this.initializeListener()

    }

    fun initializeListener(){
        this.initializeAdapter()
        with(binding){
            btnVehiculo.setOnClickListener {
                val dialog = DialogVehiculo.newInstance({
                    when (it) {
                        AccionesDialog.enviar -> {

                        }
                        else -> {

                        }
                    }
                })
                dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.styleDialogDetalle)
                dialog.show(childFragmentManager, "DIALOG ASIGNAR AUTO")
            }
        }
    }

    private fun initializeAdapter() {
        val swipegesture  = object : SwipeGestureVehiculos(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when(direction){
                    ItemTouchHelper.LEFT ->{

                    }

                }

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipegesture)
        itemTouchHelper.attachToRecyclerView( binding.rvListaVehiculos)

        binding.rvListaVehiculos.adapter = VehiculosAdapter()
    }
}