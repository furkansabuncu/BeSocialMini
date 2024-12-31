package com.furkansabuncu.besocialmini

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.furkansabuncu.besocialmini.databinding.ActivityUploadBinding
import com.google.android.material.snackbar.Snackbar

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var selectedPicture: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUploadBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        // Register activity and permission launchers
        registerLauncher()
    }

    fun upload(view: View) {
        Toast.makeText(this, "Upload functionality is not implemented yet", Toast.LENGTH_SHORT).show()
    }

    fun selectImage(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13 and above
            checkPermission(view, Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            // Android 12 and below
            checkPermission(view, Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun checkPermission(view: View, permission: String) {
        when {
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                openGallery()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(this, permission) -> {
                Snackbar.make(view, "Permission needed to access gallery", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Allow") {
                        permissionLauncher.launch(permission)
                    }.show()
            }
            else -> {
                permissionLauncher.launch(permission)
            }
        }
    }

    private fun openGallery() {
        val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activityResultLauncher.launch(intentToGallery)
    }

    private fun registerLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val intentFromResult = result.data
                if (intentFromResult != null) {
                    selectedPicture = intentFromResult.data
                    selectedPicture?.let {
                        binding.imageView.setImageURI(it)
                    }
                }
            }
        }

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                openGallery()
            } else {
                Toast.makeText(this, "Permission needed to access gallery!", Toast.LENGTH_LONG).show()
            }
        }
    }
}