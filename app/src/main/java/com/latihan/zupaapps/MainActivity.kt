package com.latihan.zupaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationButton = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navigationButton.background = null
        navigationButton.menu.getItem(2).isEnabled = false

        val Profile = findViewById<View>(R.id.mbProfile)
        Profile.setOnClickListener(){
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        val Form = findViewById<View>(R.id.mbFrom)
        Form.setOnClickListener(){
            startActivity(Intent(this,formActivity::class.java))
        }

    }

}