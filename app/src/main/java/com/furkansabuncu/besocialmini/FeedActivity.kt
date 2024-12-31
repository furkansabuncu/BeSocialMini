package com.furkansabuncu.besocialmini

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.furkansabuncu.besocialmini.databinding.ActivityFeedBinding
import com.google.firebase.auth.FirebaseAuth

class FeedActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFeedBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Binding tanımalama
        binding = ActivityFeedBinding.inflate(layoutInflater)
        var view : View = binding.root
        setContentView(view)
        //

        auth = FirebaseAuth.getInstance()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.social_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_post  ){
            val intent = Intent(this@FeedActivity, UploadActivity::class.java)
            startActivity(intent)

        }
        else if (item.itemId == R.id.sign_out  ){
            auth.signOut()
            val intent = Intent(this@FeedActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}