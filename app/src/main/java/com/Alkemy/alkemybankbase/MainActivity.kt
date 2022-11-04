package com.Alkemy.alkemybankbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.Alkemy.alkemybankbase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        observeDestinationChange(navController)


    }

    private fun observeDestinationChange(navController: NavController) {
        navController.addOnDestinationChangedListener { controller, destination, listener ->
            when(destination.id){
                R.id.homeFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.loginFragment -> {
                    //This visibility is VISIBLE only for now just to verify bottomNav functionality
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.balanceFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.topUpBalanceFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.sendMoneyFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.transactionsFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }
}