package com.example.noteapp.ui.activity


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.utils.PreferenceHelper
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedPreferences = PreferenceHelper()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBar: ActionBarDrawerToggle
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences.unit(this)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        val navController = navHostFragment.navController
        drawerLayout = binding.main
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.noteFragment) {
                binding.navView.visibility = View.VISIBLE
            } else {
                binding.navView.visibility = View.GONE
            }
        }
        setupListeners()
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            binding.main.closeDrawers()
            when (menuItem.itemId) {
                R.id.noteFragment -> {
                    navController.navigate(R.id.noteFragment)
                    true
                }
                R.id.onBoardFragment -> {
                    navController.navigate(R.id.onBoardFragment)
                    true
                }
                else -> false
            }
        }

        if (sharedPreferences.isOnboard){
            navController.navigate(R.id.onBoardFragment)
        } else {
            navController.popBackStack()
            if (sharedPreferences.isLogged){
                navController.navigate(R.id.noteFragment)
            } else{
                navController.navigate(R.id.authFragment)
            }
        }
    }

    private fun setupListeners() {

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHost)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }


}