package com.cm.gas_gps.adapters.usuarios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.databinding.ItemUsuarioBinding

class UsuariosAdapter (
) : RecyclerView.Adapter<UsuariosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {
        val view = ItemUsuarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsuariosViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
//        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return 7
    }
}