package com.cm.gas_gps.ui.notificaciones

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NotificacionesViewModel (application: Application): AndroidViewModel(application){

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    fun loadingMostrar(){
        _loading.postValue(true)
    }

    fun loadingCerrar(){
        _loading.postValue(false)
    }


}