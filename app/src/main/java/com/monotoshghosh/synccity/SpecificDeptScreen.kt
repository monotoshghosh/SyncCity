package com.monotoshghosh.synccity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.monotoshghosh.synccity.databinding.ActivitySpecificDeptScreenBinding

class SpecificDeptScreen : AppCompatActivity() {
    private lateinit var binding : ActivitySpecificDeptScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpecificDeptScreenBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)

        window.apply {
            statusBarColor = ContextCompat.getColor(this@SpecificDeptScreen,android.R.color.white)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        binding.compReceivedSpecDept.setOnClickListener {
            intent = Intent(this,ComplaintsReceived::class.java)
            startActivity(intent)
        }

        binding.newProjectsSpecDept.setOnClickListener {
            intent = Intent(this,NewProjects::class.java)
            startActivity(intent)
        }

        binding.preProjectsSpecDept.setOnClickListener {
            intent = Intent(this,PreviousProjects::class.java)
            startActivity(intent)
        }




    }
}