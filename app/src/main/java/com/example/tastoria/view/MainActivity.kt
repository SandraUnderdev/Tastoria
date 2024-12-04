package com.example.tastoria.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tastoria.R
import com.example.tastoria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.nav_home)
                    true
                }
                R.id.nav_search -> {
                    navController.navigate(R.id.nav_search)
                    true
                }
                R.id.nav_saved -> {
                    navController.navigate(R.id.nav_saved)
                    true
                }
                R.id.nav_meal_planner -> {
                    navController.navigate(R.id.nav_meal_planner)
                    true
                }
                R.id.special_diet -> {
                    navController.navigate(R.id.nav_special_diet)
                    true
                }
                else -> false
            }
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_search, R.id.nav_saved, R.id.nav_meal_planner, R.id.nav_special_diet)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

    }



}