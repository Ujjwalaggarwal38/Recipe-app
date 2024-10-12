package com.example.farmersapp

import android.app.Application
import com.google.firebase.FirebaseApp

class FarmersApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this)


    }
}