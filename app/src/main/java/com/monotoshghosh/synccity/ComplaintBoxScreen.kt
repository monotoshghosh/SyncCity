package com.monotoshghosh.synccity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.monotoshghosh.synccity.databinding.ActivityComplaintBoxScreenBinding
import com.monotoshghosh.synccity.databinding.ActivityLoginScreenBinding

class ComplaintBoxScreen : AppCompatActivity() {
    private lateinit var binding: ActivityComplaintBoxScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplaintBoxScreenBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)

        window.apply {
            statusBarColor = ContextCompat.getColor(this@ComplaintBoxScreen,android.R.color.white)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding.submitBtnCompBox.setOnClickListener {
            Toast.makeText(this, "Complaint Successfully Submitted", Toast.LENGTH_SHORT).show()
            finish()
        }


    }
}