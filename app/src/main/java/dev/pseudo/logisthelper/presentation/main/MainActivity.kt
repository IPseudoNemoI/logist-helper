package dev.pseudo.logisthelper.presentation.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dev.pseudo.logisthelper.R
import dev.pseudo.logisthelper.databinding.ActivityMainBinding
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fcv_main) as NavHostFragment

        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        val white = ContextCompat.getColor(this, R.color.white)

        binding.bottomNavigationView.setBackgroundColor(white)
        binding.bottomNavigationView.backgroundTintList = ColorStateList.valueOf(white)

        binding.bottomNavigationView.itemRippleColor =
            ColorStateList.valueOf(Color.TRANSPARENT)

        binding.bottomNavigationView.itemActiveIndicatorColor =
            ColorStateList.valueOf(Color.TRANSPARENT)

        window.navigationBarColor = white
    }
}