package com.monotoshghosh.synccity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.monotoshghosh.synccity.databinding.ActivityNewProjectsBinding
import com.monotoshghosh.synccity.databinding.ActivityPreviousProjectsBinding

class PreviousProjects : AppCompatActivity() {
    private lateinit var binding: ActivityPreviousProjectsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviousProjectsBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)

        window.apply {
            statusBarColor = ContextCompat.getColor(this@PreviousProjects,android.R.color.white)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

    }
}