package com.cm.gas_gps.adapters.rutas

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.databinding.ItemRutasBinding

class RutasAdapter (
//    private val list: List<dtoHistorialPagosModel>,
//    private val ctx: Context,
//    private val viewModel: HistorialPagosViewModel,
//    private val onClickHistorial:(Int) -> Unit
) : RecyclerView.Adapter<RutasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutasViewHolder {
        val view = ItemRutasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RutasViewHolder(view)
    }

    override fun onBindViewHolder(holder: RutasViewHolder, position: Int) {
//        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return 7
    }
}