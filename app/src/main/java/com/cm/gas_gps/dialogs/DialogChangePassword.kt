package com.cm.gas_gps.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.cm.gas_gps.databinding.DialogChangePassBinding
import com.cm.gas_gps.ui.navigation.NavigationViewModel
import com.cm.gas_gps.utils.extensions.AccionesDialog
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class DialogChangePassword : DialogFragment(){

    private lateinit var binding: DialogChangePassBinding
    private lateinit var onClickAccionPass:(AccionesDialog)-> Unit
    private lateinit var context: Context
    private lateinit var correo: String
    private lateinit var viewModel: NavigationViewModel

    companion object{
        fun newInstance(onClickAccionEnviar:(AccionesDialog)-> Unit, viewModel: NavigationViewModel,correo: String): DialogChangePassword{
            val fragment = DialogChangePassword()
            fragment.onClickAccionPass = onClickAccionEnviar
            fragment.correo = correo
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogChangePassBinding.inflate(inflater,null,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NavigationViewModel::class.java]
        this.initializeListener()
        this.initializeObserver()

    }

    private fun initializeListener(){
        with(binding){
            btnAceptarDialog.setOnClickListener {
                if(txtPass.text.toString().equals("")){
                    txtPass.error = "Requerido"
                }
                else if(txtPassNew.text.toString().equals("")){
                    txtPassNew.error = "Requerido"
                }
                else if(txtPassConfirm.text.toString().equals("")){
                    txtPassConfirm.error = "Requerido"
                }
                else{
                    val password = etvPassActual.text.toString()
                    val passwordNew = etvPassNew.text.toString()
                    val passwordConfirm = etvPassConfirm.text.toString()

                    var md: MessageDigest? = null
                    try {
                        md = MessageDigest.getInstance("SHA-256")
                    } catch (e: NoSuchAlgorithmException) {
                        e.printStackTrace()
                    }

                    val hash = md!!.digest(password.toByteArray())
                    val sb = StringBuffer()
                    for (b in hash) {
                        sb.append(String.format("%02x", b))
                    }

                    val hashnew = md!!.digest(passwordNew.toByteArray())
                    val sbnew = StringBuffer()
                    for (b in hashnew) {
                        sbnew.append(String.format("%02x", b))
                    }

                    val hashconfirm = md!!.digest(passwordConfirm.toByteArray())
                    val sbconfirm = StringBuffer()
                    for (b in hashconfirm) {
                        sbconfirm.append(String.format("%02x", b))
                    }

//                    viewModel.loadingMostrar()
//                    viewModel.changePassword(sb.toString(),sbnew.toString(),sbconfirm.toString())
                }
            }
            btnCancelarDialog.setOnClickListener {
                dismiss()
            }

            txtCorreo.text = correo
        }
    }

    private fun initializeObserver(){
//        viewModel.loading.observe(viewLifecycleOwner){
//            if (it) {
//                binding.loadingChangePass.visibility = View.VISIBLE
//            }
//            else{
//                binding.loadingChangePass.visibility= View.GONE
//            }
//
//        }
//
//        viewModel.changePassword.observe(viewLifecycleOwner){
//            if(it != null){
//                if(it.httpCode >= 500){
//                    viewModel.loadingCerrar()
//                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_LONG).show()
//                }
//                else{
//                    viewModel.loadingCerrar()
//                    onClickAccionPass.invoke(AccionesDialog.enviar)
//                    dismiss()
//                }
//            }
//
//        }

    }
}