package com.cm.gas_gps.ui.vehiculos

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.R
import com.cm.gas_gps.adapters.vehiculos.VehiculosAdapter
import com.cm.gas_gps.databinding.FragmentVehiculosBinding
import com.cm.gas_gps.dialogs.DialogAsignarAuto
import com.cm.gas_gps.dialogs.DialogVehiculo
import com.cm.gas_gps.models.dtoVehiculosModel
import com.cm.gas_gps.utils.SwipeGesture.SwipeGesture
import com.cm.gas_gps.utils.SwipeGesture.SwipeGestureVehiculos
import com.cm.gas_gps.utils.extensions.AccionesDialog
import com.google.android.material.snackbar.Snackbar

class VehiculosFragment: Fragment() {
    private lateinit var binding: FragmentVehiculosBinding
    private lateinit var viewModel: VehiculosViewModel
    private lateinit var listVehiculos: MutableList<dtoVehiculosModel>
    private lateinit var lista: List<dtoVehiculosModel>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVehiculosBinding.inflate(inflater,null,false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        viewModel = ViewModelProvider(this)[VehiculosViewModel::class.java]

        this.checkPermisos()

    }

    private fun checkPermisos(){

        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)){
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    ))
            }
            else{
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    ))
            }
        }
        else{
            this.initializeListener()
            this.initializeObserver()
        }

    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.CAMERA] == true &&
            permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true &&
            permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true) {

            this.initializeListener()
            this.initializeObserver()

        }
        else{
            this.initializeListener()
            this.initializeObserver()

        }
    }


    fun initializeListener(){
        lista = listOf()
        listVehiculos = mutableListOf()
        viewModel.loadingMostrar()
        viewModel.getVehiculo()

        with(binding){

            btnVehiculo.setOnClickListener {
                val dialog = DialogVehiculo.newInstance({
                    when (it) {
                        AccionesDialog.enviar -> {
                            viewModel.loadingMostrar()
                        }
                        else -> {

                        }
                    }
                },viewModel,
                    1,
                "",
                "",
                "",
                "",
                "",
                "",
                "")
                dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.styleDialogDetalle)
                dialog.show(childFragmentManager, "DIALOG ASIGNAR AUTO")
            }

            btnBuscar.setOnClickListener {
                viewModel.loadingMostrar()
                viewModel.getVehiculo()
            }
        }
    }

    private fun initializeObserver(){
        viewModel.loading.observe(viewLifecycleOwner){
            if (it) {
                binding.loadingCars.visibility = View.VISIBLE
            }
            else{
                binding.loadingCars.visibility= View.GONE
            }
        }

        viewModel.vehiculos.observe(viewLifecycleOwner){

            if (it != null && it.isNotEmpty()) {

                if(binding.edtBuscar.text.toString().equals("")){
                    binding.txtSinRegistros.visibility = View.GONE
                    listVehiculos = it.toMutableList()
                    this.initializeAdapter(it.toMutableList())
                }
                else{
                    it.forEach { item ->
                        Log.e("item", item.matricula)
                        Log.e("item", binding.edtBuscar.text.toString())
                        if(item.matricula.equals(binding.edtBuscar.text.toString())){
                            lista += listOf(item)
                        }
                    }

                    if(lista.size > 0) {
                        binding.txtSinRegistros.visibility = View.GONE
                        initializeAdapter(lista.toMutableList())
                    }
                    else{
                        binding.txtSinRegistros.visibility = View.VISIBLE
                    }
                }

            } else{
                viewModel.loadingCerrar()
                binding.txtSinRegistros.visibility = View.VISIBLE
                binding.rvListaVehiculos.visibility = View.GONE
            }
        }

        viewModel.vehiculosInsert.observe(viewLifecycleOwner){
            if(it != null && it.isNotEmpty()){
                Log.e("it", it.toString())


                val snack = Snackbar.make(
                    binding.root,
                    it.toString(),
                    Snackbar.LENGTH_LONG
                )
                snack.setBackgroundTint(Color.parseColor("#4B702D"))
                snack.setTextColor(Color.parseColor("#FFFFFF"))
                snack.duration = 3500
                val snackbarLayout: View = snack.getView()
                val lp = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                lp.setMargins(50, 1650, 0, 0)
                snackbarLayout.setLayoutParams(lp)
                snack.show()
                viewModel.loadingMostrar()
                viewModel.getVehiculo()
            }
        }
    }

    private fun initializeAdapter(list: List<dtoVehiculosModel>) {
        val swipegesture  = object : SwipeGestureVehiculos(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when(direction){
                    ItemTouchHelper.LEFT ->{


                    }
                    ItemTouchHelper.RIGHT->{
                        val dialog = DialogVehiculo.newInstance({
                            when (it) {
                                AccionesDialog.enviar -> {
                                    viewModel.loadingMostrar()
                                }
                                else -> {

                                }
                            }
                        },viewModel,
                            2,
                            list.get(position).idVehiculo.toString(),
                            list.get(position).matricula,
                            list.get(position).modelo,
                            list.get(position).color,
                            list.get(position).imei_id,
                            list.get(position).icc_id,
                            list.get(position).imagen)
                        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.styleDialogDetalle)
                        dialog.show(childFragmentManager, "DIALOG ASIGNAR AUTO")
                    }

                }

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipegesture)
        itemTouchHelper.attachToRecyclerView( binding.rvListaVehiculos)
        viewModel.loadingCerrar()
        binding.rvListaVehiculos.visibility = View.VISIBLE
        binding.rvListaVehiculos.adapter = VehiculosAdapter(list,requireContext(),viewModel)
    }
}