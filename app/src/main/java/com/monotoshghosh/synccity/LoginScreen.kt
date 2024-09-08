package com.monotoshghosh.synccity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.monotoshghosh.synccity.databinding.ActivityLoginScreenBinding

class LoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)

        window.apply {
            statusBarColor = ContextCompat.getColor(this@LoginScreen,android.R.color.white)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

//        binding.loginBtnLoginScreen.setOnClickListener {
//
//            database = FirebaseDatabase.getInstance().getReference("RootNode(App-SyncCity)")
//
//
//            intent = Intent(this,SpecificDeptScreen::class.java)
//            startActivity(intent)
//        }

        binding.loginBtnLoginScreen.setOnClickListener {
            val department = binding.deptLogin.text.toString().trim()
            val block = binding.blockLogin.text.toString().trim()
            val email = binding.emailIDLogin.text.toString().trim()
            val password = binding.passwordLogin.text.toString().trim()

            if (department.isEmpty() || block.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            database = FirebaseDatabase.getInstance().getReference("RootNode(App-SyncCity)/Departments")

            // Query Firebase to find a matching record
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var found = false

                    for (departmentSnapshot in snapshot.children) {
                        val dept = departmentSnapshot.child("department").getValue(String::class.java)
                        val blk = departmentSnapshot.child("block").getValue(String::class.java)
                        val emailStored = departmentSnapshot.child("emailID").getValue(String::class.java)
                        val pass = departmentSnapshot.child("password").getValue(String::class.java)

                        if (dept == department && blk == block && emailStored == email && pass == password) {
                            found = true
                            break
                        }
                    }

                    if (found) {
                        Toast.makeText(this@LoginScreen, "Login Successful", Toast.LENGTH_SHORT).show()
                        // Proceed to the next screen
                        val intent = Intent(this@LoginScreen, SpecificDeptScreen::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginScreen, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@LoginScreen, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }




    }
}


