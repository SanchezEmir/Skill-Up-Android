package com.Alkemy.alkemybankbase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginRegisterHostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register_host)

        Thread.sleep(3000)
        splash.setKeepOnScreenCondition { false }

        /*binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize navHostFragment and navController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        //Method to handler the bottom Navigation visivility
        observeDestinationChangeListener(navController)*/

    }

    /*private fun observeDestinationChangeListener(navController: NavController) {
        navController.addOnDestinationChangedListener {controller, destination, arguments ->
            when(destination.id){

                R.id.splashFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                R.id.loginFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                R.id.homeFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.transactionsFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.topUpBalanceFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.paymentsLoadFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.sendMoneyFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }

            }

        }
    }*/


}