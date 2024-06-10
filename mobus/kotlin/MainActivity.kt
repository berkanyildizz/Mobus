package com.berkan.mobus_10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.Navigation
import com.berkan.mobus_10.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = Firebase.auth

        binding.signInButton.setOnClickListener { signInClicked(it) }
        binding.signUpButton.setOnClickListener { signUpClicked(it) }

        //kayıtlı kullanıcı kontrol
        val currentUser = auth.currentUser
        if (currentUser != null) {
            mainfraggec()
        }



        }


    fun signInClicked(view: View) {
        val userEmail = binding.userEmailText.text.toString()
        val password = binding.userpasswordText.text.toString()

        if (userEmail.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(userEmail,password).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    //Signed In
                    Toast.makeText(this,"Welcome: ${auth.currentUser?.email.toString()}",
                        Toast.LENGTH_LONG).show()
                    mainfraggec()
                }

            }.addOnFailureListener { exception ->
                Toast.makeText(this,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

    }

    fun signUpClicked(view: View) {
        val userEmail = binding.userEmailText.text.toString()
        val password = binding.userpasswordText.text.toString()

        if (userEmail.isNotEmpty() && password.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(userEmail,password).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    mainfraggec()
                }

            }.addOnFailureListener { exception ->
                Toast.makeText(this,exception.localizedMessage, Toast.LENGTH_LONG).show()

            }
        }

    }

    //kayıt ekranını geçme fonksiyonu
    fun mainfraggec(){
        val intent = Intent(this, mainfrag::class.java)
        startActivity(intent)
        finish()
    }


    }




