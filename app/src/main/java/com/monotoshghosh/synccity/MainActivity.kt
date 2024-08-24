package com.monotoshghosh.synccity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.monotoshghosh.synccity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)

        window.apply {
            statusBarColor = ContextCompat.getColor(this@MainActivity,android.R.color.white)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        hideNavigationBar()

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        // REGISTRATION BTN
        binding.registerBtn.setOnClickListener {
            intent = Intent(this,RegistrationScreen::class.java)
            startActivity(intent)
        }

        // LOGIN BTN
        binding.loginBtn.setOnClickListener {
            intent = Intent(this,LoginScreen::class.java)
            startActivity(intent)
        }

        // COMPLAINT BOX BTN
        binding.complainBoxBtn.setOnClickListener {
            intent = Intent(this,ComplaintBoxScreen::class.java)
            startActivity(intent)
        }





    }






    private fun hideNavigationBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}