package com.cm.gas_gps.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.cm.gas_gps.databinding.DialogAsignarAutoBinding
import com.cm.gas_gps.databinding.DialogChangePassBinding
import com.cm.gas_gps.ui.navigation.NavigationViewModel
import com.cm.gas_gps.utils.extensions.AccionesDialog

class DialogAsignarAuto : DialogFragment(){

    private lateinit var binding: DialogAsignarAutoBinding
    private lateinit var viewModel: NavigationViewModel

    companion object{
        fun newInstance(onClickAccionEnviar:(AccionesDialog)-> Unit): DialogAsignarAuto{
            val fragment = DialogAsignarAuto()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAsignarAutoBinding.inflate(inflater,null,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NavigationViewModel::class.java]
        this.initializeListener()
//        this.initializeObserver()

    }

    fun initializeListener(){
        with(binding){
            btnCancelarDialog.setOnClickListener {
                dismiss()
            }
        }
    }
}