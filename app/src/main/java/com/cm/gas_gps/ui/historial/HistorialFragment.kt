package com.cm.gas_gps.ui.historial

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
import com.cm.gas_gps.adapters.historial.HistorialAdapter
import com.cm.gas_gps.adapters.usuarios.UsuariosAdapter
import com.cm.gas_gps.databinding.FragmentHistorialBinding
import com.cm.gas_gps.dialogs.DialogAsignarAuto
import com.cm.gas_gps.utils.SwipeGesture.SwipeGesture
import com.cm.gas_gps.utils.SwipeGesture.SwipeGestureUsuarios
import com.cm.gas_gps.utils.extensions.AccionesDialog

class HistorialFragment : Fragment(){
    private lateinit var binding: FragmentHistorialBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistorialBinding.inflate(inflater,null,false)
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
        itemTouchHelper.attachToRecyclerView( binding.rvListaHistorial)

        binding.rvListaHistorial.adapter = HistorialAdapter()
    }
}