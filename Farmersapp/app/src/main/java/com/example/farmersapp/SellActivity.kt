package com.example.farmersapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SellActivity: AppCompatActivity() {
    private var currentSelectedItemId: Int = R.id.nav_sell
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sell_activity)

        val bottomnav= findViewById<BottomNavigationView>(R.id.bottombar)
        bottomnav.setOnItemSelectedListener { item ->
            if (item.itemId == currentSelectedItemId) {
                // Item reselected, handle it accordingly
                handleItemReselected(item.itemId)
                return@setOnItemSelectedListener true
            }

            currentSelectedItemId = item.itemId

            when (item.itemId) {
                R.id.nav_home -> {
                    // Handle Home navigation
                    val intent = Intent(this, FarmersScreen::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_sell -> {
                    // Handle Sell navigation
                    true
                }
                R.id.nav_transactions -> {

                    true
                }
                R.id.nav_profile -> {

                    true
                }
                else -> false
            }
        }
        bottomnav.selectedItemId = R.id.nav_sell

    }
    private fun handleItemReselected(itemId: Int) {
        // Handle reselection logic here, for example, scroll to top or refresh content
        when (itemId) {
            R.id.nav_home -> {
                // Handle home reselection (e.g., scroll to top)
            }
            R.id.nav_sell -> {
                // Handle sell reselection (e.g., refresh content)
            }
            R.id.nav_transactions -> {
                // Handle transactions reselection
            }
            R.id.nav_profile -> {
                // Handle profile reselection
            }
        }}
}