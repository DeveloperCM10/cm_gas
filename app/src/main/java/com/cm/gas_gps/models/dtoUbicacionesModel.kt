package com.cm.gas_gps.models

import com.google.gson.annotations.SerializedName

class dtoUbicacionesModel (
    @SerializedName("id_locations_pagos")
    val id_locations_pagos: Int,

    @SerializedName("nombre_location")
    val nombre_location: String,

    @SerializedName("direccion_location")
    val direccion_location: String,

    @SerializedName("telefono_location")
    val telefono_location: String,

    @SerializedName("latitud_location")
    val latitud_location: Double,

    @SerializedName("longitude_location")
    val longitude_location: Double,

    @SerializedName("tipo")
    val tipo: Int


)