package com.cm.gas_gps.adapters.vehiculos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.databinding.ItemVehiculoBinding

class VehiculosAdapter (
) : RecyclerView.Adapter<VehiculosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculosViewHolder {
        val view = ItemVehiculoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehiculosViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehiculosViewHolder, position: Int) {
//        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return 7
    }
}