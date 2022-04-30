package com.idris.crptotracker.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.idris.crptotracker.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Idris Khozema on 30/04/2022.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStartDestination()
    }

    private fun setStartDestination()
    {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation_graph)

        val navController = navHostFragment.navController
        navController.graph = graph

    }


}