package com.cm.gas_gps.adapters.vehiculos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.databinding.ItemVehiculoBinding
import com.cm.gas_gps.models.dtoVehiculosModel
import com.cm.gas_gps.ui.vehiculos.VehiculosViewModel

class VehiculosAdapter (
    private val list: List<dtoVehiculosModel>,
    private val ctx: Context,
    private val viewModel: VehiculosViewModel

) : RecyclerView.Adapter<VehiculosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculosViewHolder {
        val view = ItemVehiculoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehiculosViewHolder(view,ctx,viewModel)
    }

    override fun onBindViewHolder(holder: VehiculosViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}