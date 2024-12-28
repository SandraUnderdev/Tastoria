package com.example.tastoria.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
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
                R.id.recipeListFragment -> {
                    navController.navigate(R.id.recipeListFragment)
                    true
                }
                R.id.tastoriaSavedFragment -> {
                    navController.navigate(R.id.tastoriaSavedFragment)
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
            setOf(R.id.nav_home, R.id.recipeListFragment, R.id.tastoriaSavedFragment, R.id.nav_meal_planner, R.id.nav_special_diet)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}