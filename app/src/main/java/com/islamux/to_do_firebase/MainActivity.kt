package com.islamux.to_do_firebase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.islamux.to_do_firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Replace with your binding class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        setContentView(binding.root) // Replace with your layout

        // Enable edge-to-edge mode for the activity and its content view
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main /*findViewById(R.id.main )*/) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up your UI elements and event listeners here if needed

    }
}