package com.furkansabuncu.besocialmini

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.furkansabuncu.besocialmini.databinding.ActivityFeedBinding

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Binding tanımlama
        binding = ActivityFeedBinding.inflate(layoutInflater)
        var view : View = binding.root
        setContentView(view)
        //
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    // button fonksiyon
    fun upload(view : View){

    }
    fun selectImage(view : View ){

    }
    //
}