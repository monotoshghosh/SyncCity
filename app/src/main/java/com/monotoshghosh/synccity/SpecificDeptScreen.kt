package com.monotoshghosh.synccity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        binding.onGoingProjectsSpecDept.setOnClickListener {
            intent = Intent(this,OnGoingProjects::class.java)
            startActivity(intent)
        }

        binding.requestDepartment.setOnClickListener {
            intent = Intent(this,Requests::class.java)
            startActivity(intent)
        }

        binding.logoutDepartment.setOnClickListener {
            Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show()
            intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // TO CLEAR THE STACK
            startActivity(intent)
            finish()
        }
        binding.contOtherDeptSpecDept.setOnClickListener {
            intent = Intent(this,ContactOtherDept::class.java)
            startActivity(intent)
        }




    }
}