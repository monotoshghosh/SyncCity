package com.monotoshghosh.synccity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.monotoshghosh.synccity.databinding.ActivityRegistrationScreenBinding

class RegistrationScreen : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationScreenBinding
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationScreenBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)

        window.apply {
            statusBarColor = ContextCompat.getColor(this@RegistrationScreen,android.R.color.white)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        //hideNavigationBar()

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding.signupBtnRegistration.setOnClickListener {

            val department = binding.deptReg.text.toString()
            val town = binding.villTownReg.text.toString()
            val block = binding.blockReg.text.toString()
            val district = binding.distReg.text.toString()
            val pinCode = binding.pinCodeReg.text.toString()
            val state = binding.stateReg.text.toString()
            val phoneNo = binding.phoneNoReg.text.toString()
            val emailId = binding.emailIDReg.text.toString()
            val password = binding.passwordReg.editableText.toString()

            val specDept = Registration(department, town, block, district, pinCode, state, phoneNo, emailId, password)
            database = FirebaseDatabase.getInstance().getReference("RootNode(App-SyncCity)")


            val childNode = "$department - $block"

            database.child(childNode).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // Child node exists
                        Toast.makeText(this@RegistrationScreen, "Department Already Exist", Toast.LENGTH_SHORT).show()
                    } else {
                        // Child node does not exist, proceed with registration
                        database.child(childNode).setValue(specDept).addOnSuccessListener {
                            Toast.makeText(this@RegistrationScreen, "Registration Success", Toast.LENGTH_SHORT).show()
                            binding.deptReg.text?.clear()
                            binding.villTownReg.text?.clear()
                            binding.blockReg.text?.clear()
                            binding.distReg.text?.clear()
                            binding.pinCodeReg.text?.clear()
                            binding.stateReg.text?.clear()
                            binding.phoneNoReg.text?.clear()
                            binding.emailIDReg.text?.clear()
                            binding.passwordReg.text?.clear()
                        }.addOnFailureListener {
                            Toast.makeText(this@RegistrationScreen, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@RegistrationScreen, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })

//            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
//            intent = Intent(this,LoginScreen::class.java)
//            startActivity(intent)
//            finish()
        }

    }





    private fun hideNavigationBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}