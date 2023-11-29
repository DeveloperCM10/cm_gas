package com.cm.gas_gps.dialogs

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.cm.gas_gps.BuildConfig
import com.cm.gas_gps.R
import com.cm.gas_gps.databinding.DialogAsignarAutoBinding
import com.cm.gas_gps.databinding.DialogVehiculosAddBinding
import com.cm.gas_gps.models.dtoVehiculoInsertModel
import com.cm.gas_gps.ui.navigation.NavigationViewModel
import com.cm.gas_gps.ui.vehiculos.VehiculosViewModel
import com.cm.gas_gps.utils.extensions.AccionesDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_vehiculos_add.*
import java.io.File

class DialogVehiculo : DialogFragment(){

    private lateinit var binding: DialogVehiculosAddBinding
    private lateinit var viewModel: VehiculosViewModel
    private lateinit var photoURI : Uri
    private lateinit var path: String
    private lateinit var fileImagen: File
    private lateinit var placa: String
    private lateinit var modelo: String
    private lateinit var imei: String
    private lateinit var icc: String
    private lateinit var color: String
    private var accion: Int = 0

    private lateinit var placaE: String
    private lateinit var modeloE: String
    private lateinit var imeiE: String
    private lateinit var iccE: String
    private lateinit var colorE: String
    private lateinit var imagenE: String
    private lateinit var idVehiculoE: String


    companion object{
        fun newInstance(onClickAccionEnviar:(AccionesDialog)-> Unit, viewModel:  VehiculosViewModel, accion: Int,
                        idVehiculoE: String,
                        placaE: String,
                        modeloE: String,
                        colorE: String,
                        imeiE: String,
                        iccE: String,
                        imagenE: String
                        ): DialogVehiculo{
            val fragment = DialogVehiculo()
            fragment.viewModel = viewModel
            fragment.accion = accion
            fragment.idVehiculoE = idVehiculoE
            fragment.placaE = placaE
            fragment.imeiE = imeiE
            fragment.colorE = colorE
            fragment.modeloE = modeloE
            fragment.iccE = iccE
            fragment.imagenE = imagenE
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogVehiculosAddBinding.inflate(inflater,null,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initializeListener()
    }

    fun initializeListener(){
        with(binding){
            btnCancelarDialog.setOnClickListener {
                viewModel.getVehiculo()
                dismiss()
            }

            if(accion == 1){
                edtPlaca.setText("")
                edtModelo.setText("")
                edtColor.setText("")
                edtImei.setText("")
                edtIcc.setText("")

                Picasso.get()
                    .load(R.drawable.ic_add_picture)
                    .into(imgAddPicture)

                edtImei.isEnabled= true
                edtIcc.isEnabled= true
            }
            else{
                edtPlaca.setText(placaE)
                edtModelo.setText(modeloE)
                edtColor.setText(colorE)
                edtImei.setText(imeiE)
                edtIcc.setText(iccE)

                edtImei.isEnabled= false
                edtIcc.isEnabled= false

                Picasso.get()
                    .load(imagenE)
                    .into(imgAddPicture)
            }

            btnAceptarDialog.setOnClickListener {
                if(edtPlaca.text.toString().equals("")){
                    edtPlaca.error = "Requerido"
                }
                else if(edtImei.text.toString().equals("")){
                    edtImei.error = "Requerido"
                }
                else if(edtColor.text.toString().equals("")){
                    edtColor.error = "Requerido"
                }
                else if(edtIcc.text.toString().equals("")){
                    edtIcc.error = "Requerido"
                }
                else if(edtModelo.text.toString().equals("")){
                    edtModelo.error = "Requerido"
                }
                else{
                    placa = edtPlaca.text.toString()
                    modelo = edtModelo.text.toString()
                    color = edtColor.text.toString()
                    imei = edtImei.text.toString()
                    icc = edtIcc.text.toString()

                    if(accion == 1) {
                            //INSERTAR
                        var dtoVehiculoInsertModel = dtoVehiculoInsertModel(
                            placa,
                            modelo,
                            color,
                            imei,
                            icc,
                            "https://www.toyota.mx/sites/default/files/modelos/yaris-sedan/descubre/yaris_bucket2.png"
                        )

                        viewModel.insertVehiculo(
                            dtoVehiculoInsertModel
                        )
                    }
                    else{
                        //EDITAR
                    }
                    dialog?.hide()
                }
            }

            imgAddPicture.setOnClickListener {
                photoURI = FileProvider.getUriForFile(
                    requireContext(),
                    BuildConfig.APPLICATION_ID + ".provider",
                    createImageFile()!!
                )
                if (photoURI.toString() != "") {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    mStartForResult.launch(intent)
                } else {
                    Log.e("aqui", "sin uri")
                }
            }
        }
    }

    fun createImageFile(): File? {
        val consecutivo = System.currentTimeMillis() / 1000
        val nombre = "$consecutivo.jpg"
        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
            "Camara"
        )
        storageDir.mkdir()
        path = storageDir.toString() + File.separator + nombre
        fileImagen = File(path)
        return fileImagen
    }

    var mStartForResult = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.e("ActivityResult", photoURI.toString())
        if (result.resultCode == Activity.RESULT_OK) {
            binding.imgAddPicture.setImageURI(photoURI)

        }
    }
}