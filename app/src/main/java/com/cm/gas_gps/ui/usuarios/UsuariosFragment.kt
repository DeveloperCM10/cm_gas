package com.cm.gas_gps.ui.usuarios

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.R
import com.cm.gas_gps.adapters.rutas.RutasAdapter
import com.cm.gas_gps.adapters.usuarios.UsuariosAdapter
import com.cm.gas_gps.databinding.FragmentUsuariosBinding
import com.cm.gas_gps.dialogs.DialogAsignarAuto
import com.cm.gas_gps.dialogs.DialogChangePassword
import com.cm.gas_gps.utils.SwipeGesture.SwipeGestureUsuarios
import com.cm.gas_gps.utils.SwipeGesture.SwipeGestureVehiculos
import com.cm.gas_gps.utils.extensions.AccionesDialog
import com.google.android.material.snackbar.Snackbar

class UsuariosFragment: Fragment() {
    private lateinit var binding: FragmentUsuariosBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsuariosBinding.inflate(inflater,null,false)
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
            btnAddAsignar.setOnClickListener {
                val dialog = DialogAsignarAuto.newInstance({
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
        val swipegesture  = object : SwipeGestureUsuarios(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when(direction){
                    ItemTouchHelper.LEFT ->{

                    }

                }

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipegesture)
        itemTouchHelper.attachToRecyclerView( binding.rvListaUsuarios)

        binding.rvListaUsuarios.adapter = UsuariosAdapter()
    }
}