package com.cm.gas_gps.api

import com.cm.gas_gps.models.dtoVehiculoInsertModel
import com.cm.gas_gps.models.dtoVehiculosModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiVehiculosInterface {

    @POST("vehiculo")
    fun insertVehiculo(@Body dtoVehiculoInsertModel: dtoVehiculoInsertModel): Call<String>

    @GET("vehiculo")
    fun getVehiculo(): Call<List<dtoVehiculosModel>>
}