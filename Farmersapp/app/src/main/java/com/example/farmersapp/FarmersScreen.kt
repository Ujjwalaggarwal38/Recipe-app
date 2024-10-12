package com.example.farmersapp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FarmersScreen: AppCompatActivity() {
    private var currentSelectedItemId: Int = R.id.nav_home
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.farmers_screen)

        val notificationIcon=findViewById<FloatingActionButton>(R.id.notificationicon)
        val markettrends=findViewById<ImageView>(R.id.markettrends)

        markettrends.setOnClickListener{
            val intent = Intent(this, MarketTrends::class.java)
            startActivity(intent)
        }

        notificationIcon.setOnClickListener {
            // Inflate the notification popup layout
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.notification, null)

            val width = (200 * resources.displayMetrics.density).toInt()
            val height = (150 * resources.displayMetrics.density).toInt()

            // Create a PopupWindow
            val popupWindow = PopupWindow(
                popupView,
                width,
                height,
                true
            )
            popupWindow.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, android.R.color.white)))
            popupWindow.elevation = 10f
            popupWindow.showAsDropDown(notificationIcon, 0, 0)
        }

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
                    true
                }
                R.id.nav_sell -> {
                    // Handle Sell navigation
                    val intent = Intent(this, SellActivity::class.java)
                    startActivity(intent)
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
        bottomnav.selectedItemId = R.id.nav_home

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