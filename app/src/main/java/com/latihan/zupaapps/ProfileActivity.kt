package com.latihan.zupaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val navigationButton = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navigationButton.background = null
        navigationButton.menu.getItem(2).isEnabled = false

        val editProfile = findViewById<Button>(R.id.editProfile)
        editProfile.setOnClickListener(){
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

    }

}