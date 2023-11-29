package com.cm.gas_gps.adapters.vehiculos

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.databinding.ItemVehiculoBinding
import com.cm.gas_gps.models.dtoVehiculosModel
import com.cm.gas_gps.ui.vehiculos.VehiculosViewModel
import com.squareup.picasso.Picasso

class VehiculosViewHolder (
    private val binding: ItemVehiculoBinding,
    private val ctx: Context,
    private val viewModel: VehiculosViewModel

    ): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: dtoVehiculosModel) {
        with(binding) {
            txtNumeroPlaca.text = "Placa: " + model.matricula
            txtModelo.text = "Modelo: "+model.modelo
            txtImei.text = "IMEI: " + model.imei_id
            txtColor.text = "Color: " + model.color
            txtIcc.text = "ICC: " + model.icc_id

            Picasso.get()
                .load(model.imagen)
                .into(imgRuta)

        }
    }

}