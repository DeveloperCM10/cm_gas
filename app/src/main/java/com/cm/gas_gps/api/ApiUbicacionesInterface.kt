package com.cm.gas_gps.api

import com.cm.gas_gps.models.DataUbicacionesModel
import retrofit2.Call
import retrofit2.http.POST

interface ApiUbicacionesInterface {

    @POST("references/locations_offices_pagos")
    fun getUbicaciones(): Call<DataUbicacionesModel>
}