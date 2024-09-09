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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.monotoshghosh.synccity.databinding.ActivityComplaintBoxScreenBinding
import com.monotoshghosh.synccity.databinding.ActivityLoginScreenBinding

class ComplaintBoxScreen : AppCompatActivity() {
    private lateinit var binding: ActivityComplaintBoxScreenBinding
    lateinit var database: DatabaseReference

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

            val name = binding.nameCompBox.text.toString()
            val phoneNo = binding.phoneNoCompBox.text.toString()
            val compDesc = binding.compComplaintBox.text.toString()
            val dept1 = binding.dept1CompBox.text.toString()
            val dept2 = binding.dept2CompBox.text.toString()
            val dept3 = binding.dept3CompBox.text.toString()

            if(name.isEmpty() || phoneNo.isEmpty() || compDesc.isEmpty() || dept1.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val complainNode = ComplainBox(name, phoneNo, compDesc, dept1, dept2, dept3)

            database = FirebaseDatabase.getInstance().getReference("RootNode(App-SyncCity)/Complaints")

            val childNode = "$name - $phoneNo"

            database.child(childNode).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // Child node exists
                        Toast.makeText(this@ComplaintBoxScreen, "Complaint Already Registered", Toast.LENGTH_SHORT).show()
                    } else {
                        // Child node does not exist, proceed with registration
                        database.child(childNode).setValue(complainNode).addOnSuccessListener {
                            Toast.makeText(this@ComplaintBoxScreen, "Complaint Successfully Registered", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext,MainActivity::class.java))
                            finish()

                        }.addOnFailureListener {
                            Toast.makeText(this@ComplaintBoxScreen, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ComplaintBoxScreen, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })




            Toast.makeText(this, "Complaint Successfully Submitted", Toast.LENGTH_SHORT).show()
            finish()
        }


    }
}