package com.furkansabuncu.besocialmini

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.furkansabuncu.besocialmini.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

     private lateinit var binding : ActivityMainBinding
     private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Binding ve auth tanımlama
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()
        //



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    // Butonlar
    fun signInClicked(view : View ){

    }

    fun signUpClicked(view : View ){
        val email = binding.emailText.text.toString()
        val pass= binding.passwordText.text.toString()

        if(email!="" && pass !=""){
            auth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener {
                val intent = Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish() // kullanıcı bir daha buraya dönmez finish ile


            }.addOnFailureListener {
                Toast.makeText(this@MainActivity, it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(this,"Please enter your email and password",Toast.LENGTH_LONG).show()
        }

    }
    //Butonlar sonu
}