package com.cm.gas_gps.models

import com.google.gson.annotations.SerializedName

class dtoVehiculosModel (

    @SerializedName("idVehiculo")
    val idVehiculo: Int,

    @SerializedName("matricula")
    val matricula: String,

    @SerializedName("modelo")
    val modelo: String,

    @SerializedName("color")
    val color: String,

    @SerializedName("imei_id")
    val imei_id: String,

    @SerializedName("icc_id")
    val icc_id: String,

    @SerializedName("imagen")
    val imagen: String,

    @SerializedName("fechaCreacion")
    val fechaCreacion: String,

    @SerializedName("fechaModificacion")
    val fechaModificacion: String

)