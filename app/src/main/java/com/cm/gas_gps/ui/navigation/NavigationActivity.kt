package com.cm.gas_gps.ui.navigation

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.cm.gas_gps.R
import com.cm.gas_gps.databinding.ActivityNavigationBinding
import com.cm.gas_gps.dialogs.DialogChangePassword
import com.cm.gas_gps.ui.rutas.RutasFragment
import com.cm.gas_gps.utils.extensions.AccionesDialog
import com.cm.gas_gps.utils.extensions.sharedPreferences
import com.google.android.material.snackbar.Snackbar


class NavigationActivity : AppCompatActivity(){
    private lateinit var binding: ActivityNavigationBinding
    private  lateinit var navController: NavController
    private val viewModel: NavigationViewModel by viewModels()

    private val sharedPreferences: sharedPreferences by lazy {
        sharedPreferences(application.applicationContext)
    }

    companion object{
        fun startActivity(context: Context){
            val intent = Intent(context, NavigationActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onBackPressed() {
        super.onBackPressed()

        navController.popBackStack()
    }

    override fun onResume() {
        super.onResume()
        initializeNavigation()
    }

    @SuppressLint("ResourceAsColor")
    private fun initializeNavigation(){
        navController = (supportFragmentManager.findFragmentById(
            R.id.acvityNavigationNav)as NavHostFragment)
            .navController

        with(binding){

            activityNavigationMenuTop.setupWithNavController(navController)
            activityNavigationMenuBottom.setOnItemSelectedListener {item ->
                when(item.itemId){
                    R.id.action_mapa -> mapa()
                    R.id.action_rutas -> rutas()
                    R.id.action_consumos -> consumos()

                }
                true
            }

            activityNavigationMenuTop.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener {
                    item ->
                when(item.itemId){
                    R.id.ic_notification -> notificaciones()
                    R.id.action_users ->users()
                    R.id.action_cars ->cars()
                    R.id.action_change_pass ->change_pass()
                    R.id.action_logout ->logout()
                }
                true
            })

        }

    }

    fun mapa(){
        navController.navigate(R.id.fragment_maps)

    }

    fun rutas(){
        navController.navigate(R.id.fragment_rutas)
    }

    fun consumos(){
        navController.navigate(R.id.fragment_consumos)
    }

    fun notificaciones(){
        navController.navigate(R.id.fragment_notificaciones)

    }

    fun users(){
        navController.navigate(R.id.fragment_users)

    }

    fun cars(){
        navController.navigate(R.id.fragment_cars)

    }

    fun change_pass(){
        val dialog = DialogChangePassword.newInstance({
            when (it) {
                AccionesDialog.enviar -> {
                    val snack = Snackbar.make(
                        binding.root,
                        "Contraseña actualizada exitosamente",
                        Snackbar.LENGTH_LONG
                    )
                    snack.setBackgroundTint(Color.parseColor("#4B702D"))
                    snack.setTextColor(Color.parseColor("#FFFFFF"))
                    snack.duration = 5000
                    val snackbarLayout: View = snack.getView()
                    val lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    lp.setMargins(50, 1650, 0, 0)
                    snackbarLayout.setLayoutParams(lp)
                    snack.show()
                }
                else -> {

                }
            }
        }, viewModel, sharedPreferences.getCorreo()!!)
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.styleDialogDetalle)
        dialog.show(getSupportFragmentManager(), "DIALOG RESET PASSWORD")

    }

    fun logout(){
        finish()
    }

}