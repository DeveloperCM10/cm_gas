package com.cm.gas_gps.ui.ubicaciones

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cm.gas_gps.api.ApiService
import com.cm.gas_gps.api.ApiUbicacionesInterface
import com.cm.gas_gps.models.DataUbicacionesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UbicacionesViewModel (application: Application): AndroidViewModel(application){

    private val service: ApiUbicacionesInterface by lazy {
        ApiService.getInstance().create(ApiUbicacionesInterface::class.java)
    }

    private val _ubicaciones: MutableLiveData<DataUbicacionesModel?> = MutableLiveData()
    val ubicaciones: LiveData<DataUbicacionesModel?> get() = _ubicaciones

    fun ubicaiones() = viewModelScope.launch(
        Dispatchers.IO){
        val call: Call<DataUbicacionesModel> = service.getUbicaciones()

        //SI NO ESTAN HECHAS EN LAMBADA SE LLAMA POR OBJECT
        call.enqueue(object: Callback<DataUbicacionesModel> {
            override fun onResponse(
                call: Call<DataUbicacionesModel>,
                response: Response<DataUbicacionesModel>
            ) {

                val usuario = response.body()

                //LET OBTENER VALOR DE LA VARIBALE
                usuario.let {

                    if (it != null) {
                        _ubicaciones.postValue(it)
                    }
                    else{

                        _ubicaciones.postValue(
                            DataUbicacionesModel(
                                listOf()
                            )
                        )
                    }

                }
            }

            override fun onFailure(call: Call<DataUbicacionesModel>, t: Throwable) {
                Log.e("FALLO_UBICACIONES", t.message.toString())

                _ubicaciones.postValue(
                    DataUbicacionesModel(
                        listOf()
                    )
                )
            }

        })
    }

    private val _vehiculos: MutableLiveData<DataUbicacionesModel?> = MutableLiveData()
    val vehiculos: LiveData<DataUbicacionesModel?> get() = _vehiculos

    fun vehiculos() = viewModelScope.launch(
        Dispatchers.IO){
        val call: Call<DataUbicacionesModel> = service.getUbicaciones()

        //SI NO ESTAN HECHAS EN LAMBADA SE LLAMA POR OBJECT
        call.enqueue(object: Callback<DataUbicacionesModel> {
            override fun onResponse(
                call: Call<DataUbicacionesModel>,
                response: Response<DataUbicacionesModel>
            ) {

                val usuario = response.body()

                //LET OBTENER VALOR DE LA VARIBALE
                usuario.let {

                    if (it != null) {
                        _vehiculos.postValue(it)
                    }
                    else{

                        _vehiculos.postValue(
                            DataUbicacionesModel(
                                listOf()
                            )
                        )
                    }

                }
            }

            override fun onFailure(call: Call<DataUbicacionesModel>, t: Throwable) {
                Log.e("FALLO_UBICACIONES", t.message.toString())

                _vehiculos.postValue(
                    DataUbicacionesModel(
                        listOf()
                    )
                )
            }

        })
    }
}