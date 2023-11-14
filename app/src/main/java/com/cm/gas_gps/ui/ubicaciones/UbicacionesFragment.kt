package com.cm.gas_gps.ui.ubicaciones

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cm.gas_gps.R
import com.cm.gas_gps.databinding.FragmentUbicacionesBinding
import com.cm.gas_gps.models.dtoUbicacionesModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class UbicacionesFragment : Fragment(), OnMapReadyCallback,
    GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMarkerClickListener {
    private lateinit var binding: FragmentUbicacionesBinding
    private val viewModel: UbicacionesViewModel by viewModels()
    private lateinit var map:GoogleMap
    var listUbicaciones: List<dtoUbicacionesModel> = listOf()
    var listVehiculos: List<dtoUbicacionesModel> = listOf()
    lateinit var markerLocation: Location
    lateinit var variableYo: LatLng
    lateinit var marker: Location
    lateinit var markerDatos: Marker
    lateinit var locationManager: LocationManager
    lateinit var finRuta : LatLng
    var googleMap:GoogleMap?= null
    var poly: Polyline? = null
    var polylineOptions : PolylineOptions? = null
    var polyline: Polyline? = null

        companion object{
        const val REQUEST_LOCATION = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUbicacionesBinding.inflate(inflater,null,false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        this.checkPermisos()

    }

    fun initializeListener(){
        listVehiculos = listOf()
        listUbicaciones = listOf()
        if(ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission
                .ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            this.createMaps()
        }
        else{
            Log.e("aqui","1")
            this.createMaps()
        }

    }

    fun initializeObserver(){
        viewModel.ubicaciones.observe(viewLifecycleOwner){
            if (it != null) {
                listUbicaciones = it.data
                createRoute()
            }
        }

        viewModel.vehiculos.observe(viewLifecycleOwner){
            if (it != null) {
                listVehiculos= it.data
                createMarkerVehiculos()
                createMarkerPersona()
            }
        }

    }

    private fun createMarkerVehiculos(){

        for (i in 0..listVehiculos.size -1) {
            markerLocation = Location("")
            markerLocation?.setLatitude(listVehiculos.get(i).latitud_location)
            markerLocation?.setLongitude(listVehiculos.get(i).longitude_location)
            val variable = LatLng(
                listVehiculos.get(i).latitud_location,
                listVehiculos.get(i).longitude_location
            )
            markerDatos = map.addMarker(
                MarkerOptions()
                    .position(variable)
                    .title(listVehiculos.get(i).nombre_location)
                    .snippet(listVehiculos.get(i).direccion_location)
                    .icon(bitmapDescriptorFromVector(requireContext(),R.drawable.car_img))
            )!!

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(variable, 13f))

        }

    }

    private fun checkPermisos(){

        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)){
                Log.e("aqui", "llego1")
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ))
            }
            else{
                Log.e("aqui", "llego2")
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ))
            }
        }
        else{

            Log.e("aqui", "llego3")
            this.initializeListener()
            this.initializeObserver()
        }

    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.POST_NOTIFICATIONS] == true &&
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {

            this.initializeListener()

        }
        else{
            this.initializeListener()

        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled = true
            }
            else{
                enableLocation()
                Toast.makeText(requireContext(),"Ve a ajustes y acepta los permisos para continuar usando la app", Toast.LENGTH_LONG).show()
            }
            else -> {
                enableLocation()
            }
        }
    }
    @SuppressLint("MissingPermission")
    private fun createMarkerPersona() {
        Log.e("persona","aqui")

            locationManager =
                requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val providers: List<String> = locationManager.getProviders(true)
            for (provider in providers) {
                val l: Location = locationManager.getLastKnownLocation(provider) ?: continue
                variableYo = LatLng(l.latitude, l.longitude)
                marker = Location("")
                marker.latitude = l.latitude
                marker.longitude = l.longitude

            }

            markerDatos = map.addMarker(
                MarkerOptions()
                    .position(variableYo)
                    .title("Mi ubicación")
                    .snippet("Mi ubicación")
                    .icon(bitmapDescriptorFromVector(requireContext(),  R.drawable.ic_ubicacion_actual))
            )!!
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(variableYo, 13f))

    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun createMaps() {
        val mapFragment = childFragmentManager.findFragmentByTag("map") as SupportMapFragment?
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.map = googleMap
        viewModel.vehiculos()
       map.setOnMyLocationButtonClickListener(this)
                map.setOnMarkerClickListener(this)
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    )== PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    private fun enableLocation(){
        if(!::map.isInitialized) return
        if(isLocationPermissionGranted()){
            map.isMyLocationEnabled = true
        }
        else{
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(requireContext(),"Ve a ajustes y acepta los permisos", Toast.LENGTH_LONG).show()
        }
        else{
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ))
        }
    }


    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(requireContext(),"Mi Ubicación actual", Toast.LENGTH_LONG).show()
        return false
    }

    override fun onMarkerClick(market: Marker): Boolean {
        listUbicaciones = listOf()
        polyline?.remove()
       listVehiculos.forEach {
           if(market.title == it.nombre_location){
               finRuta = LatLng(it.latitud_location,it.longitude_location)
               viewModel.ubicaiones()
           }
       }

        return false
    }

    fun createRoute(){

        activity?.runOnUiThread{
        var lat: LatLng
        val polylinesLt = ArrayList<LatLng>()

            listUbicaciones.forEach {
                lat =  LatLng(it.latitud_location,it.longitude_location)
                polylinesLt.add(lat)
            }


//            polylineOptions = PolylineOptions()
//                .clickable(true)
//                .addAll(
//                   polylinesLt
//                )
//                .color(Color.BLUE)


            polylineOptions = PolylineOptions()
                .clickable(true)
                .add(
                    LatLng(20.601540719203893, -100.3848100256054),
                    LatLng(20.603709932927654, -100.37768607881522),
                    LatLng(20.60105866751711, -100.37729984073623),
                    LatLng(20.593184940849447, -100.37910228510485),
                    LatLng(20.596077377489717, -100.36167865620835),
                    finRuta

                )
                .color(Color.BLUE)

            polyline = map.addPolyline(polylineOptions!!)
            polyline!!.startCap = CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.fin_ruta))



        }
    }

}
