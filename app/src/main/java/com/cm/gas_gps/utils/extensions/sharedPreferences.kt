package com.cm.gas_gps.utils.extensions

import android.content.Context

class sharedPreferences (
    private val context: Context
) {

    companion object {
        private const val TAG_CORREO = "CORREO"
        private const val TAG_NOMBRE = "NOMBRE"
    }

    private val localStorage =
        context.getSharedPreferences("cxpApp", Context.MODE_PRIVATE)


    fun setCorreo(correo: String) {
        with(localStorage.edit()) {
            putString(TAG_CORREO, correo).commit()
        }
    }

    fun getCorreo(): String? = localStorage.getString(TAG_CORREO, "")

}