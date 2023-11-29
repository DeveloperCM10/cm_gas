package com.cm.gas_gps.ui.vehiculos

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cm.gas_gps.api.ApiService
import com.cm.gas_gps.api.ApiVehiculosInterface
import com.cm.gas_gps.models.dtoVehiculoInsertModel
import com.cm.gas_gps.models.dtoVehiculosModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VehiculosViewModel (application: Application): AndroidViewModel(application){
    private lateinit var usuario: String

    private val service: ApiVehiculosInterface by lazy{
        ApiService.getInstance().create(ApiVehiculosInterface::class.java)
    }

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    fun loadingMostrar(){
        _loading.postValue(true)
    }

    fun loadingCerrar(){
        _loading.postValue(false)
    }


    private val _vehiculos: MutableLiveData<List<dtoVehiculosModel>> = MutableLiveData()
    val vehiculos: LiveData<List<dtoVehiculosModel>> get() = _vehiculos

    fun getVehiculo() = viewModelScope.launch(
        Dispatchers.IO){
        val call: Call<List<dtoVehiculosModel>> = service.getVehiculo()

        //SI NO ESTAN HECHAS EN LAMBADA SE LLAMA POR OBJECT
        call.enqueue(object: Callback<List<dtoVehiculosModel>> {
            override fun onResponse(
                call: Call<List<dtoVehiculosModel>>,
                response: Response<List<dtoVehiculosModel>>
            ) {

                val usuario = response.body()
                Log.e("datosResponse", usuario.toString())
                //LET OBTENER VALOR DE LA VARIBALE
                usuario.let {

                    if (it != null) {
                        _vehiculos.postValue(it)
                    }
                    else{

                        _vehiculos.postValue(
                            emptyList()
                        )
                    }

                }
            }

            override fun onFailure(call: Call<List<dtoVehiculosModel>>, t: Throwable) {
                Log.e("FALLO_VEHICULOS_GET", t.message.toString())

                _vehiculos.postValue(
                    emptyList()
                )
            }

        })
    }


    private val _vehiculosInsert: MutableLiveData<String> = MutableLiveData()
    val vehiculosInsert: LiveData<String> get() = _vehiculosInsert

    fun insertVehiculo(
        dtoVehiculoInsertModel: dtoVehiculoInsertModel) = viewModelScope.launch(
        Dispatchers.IO){

        val call: Call<String> = service.insertVehiculo(dtoVehiculoInsertModel)

        //SI NO ESTAN HECHAS EN LAMBADA SE LLAMA POR OBJECT
        call.enqueue(object: Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {

                 usuario = response.body().toString()

                //LET OBTENER VALOR DE LA VARIBALE
                usuario.let {

                    if (it != null) {
                        _vehiculosInsert.postValue(
                           usuario
                        )
                    }
                    else{

                        _vehiculosInsert.postValue(
                            usuario
                        )
                    }

                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("FALLO_VEHICULOS_INSERT", t.message.toString())

                _vehiculosInsert.postValue(
                    usuario
                )
            }

        })
    }

}