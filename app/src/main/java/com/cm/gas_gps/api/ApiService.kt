package com.cm.gas_gps.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    companion object {
//        const val ENDPOINT = "https://pagos.gphsis.com/api/"

        const val ENDPOINT="http://192.168.30.135:3000/api/"
        private lateinit var retrofit: Retrofit

        fun getInstance(): Retrofit {
            if (!this::retrofit.isInitialized) {

//                OkHttpClient.Builder
                val okHttpClient = OkHttpClient().newBuilder()

//                OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .callTimeout(10, TimeUnit.MINUTES)
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build()


                retrofit = Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                Log.e("retrofit", retrofit.toString())
            } else {
                Log.e("retrofit", retrofit.toString())
            }

            return retrofit
        }
    }
}