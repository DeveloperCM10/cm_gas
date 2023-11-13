package com.cm.gas_gps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cm.gas_gps.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Handler().postDelayed({
//            if(sharedPreferences.getLogin() == 1){
//                finish()
//                startActivity(Intent(applicationContext, NavigationActivity::class.java))
//            }
//            else{
                finish()
                startActivity(Intent(applicationContext, MainActivity::class.java))
//            }
        }, 1500)


    }


}