package com.cm.gas_gps.adapters.notificaciones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.databinding.ItemNotificacionesBinding

class NotificacionesAdapter (
) : RecyclerView.Adapter<NotificacionesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificacionesViewHolder {
        val view = ItemNotificacionesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificacionesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificacionesViewHolder, position: Int) {
//        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return 7
    }
}