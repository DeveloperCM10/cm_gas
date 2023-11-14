package com.cm.gas_gps.models

import com.google.gson.annotations.SerializedName

class DataUbicacionesModel (
    @SerializedName("data")
    val data: List<dtoUbicacionesModel>
)