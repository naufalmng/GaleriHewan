package org.d3if2146.galerihewan.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import org.d3if2146.galerihewan.R


class MainActivity : AppCompatActivity() {
    private val navController : NavController by lazy {
        findNavController(R.id.fragmentContainer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupActionBarWithNavController(this,navController)
      }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}